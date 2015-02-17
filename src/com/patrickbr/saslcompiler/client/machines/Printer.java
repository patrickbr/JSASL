package com.patrickbr.saslcompiler.client.machines;


import java.util.Stack;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.Exportable;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.patrickbr.saslcompiler.client.exceptions.ReduceException;
import com.patrickbr.saslcompiler.client.nodes.*;

@ExportPackage("sasl")
@Export
public class Printer implements Exportable {
	private int cancelRun = -1;
	private int run = 0;
	
	public Printer() {
	}
	
	public void cancel() {
		cancelRun = this.run;
	}
	
	private void printHelper(final Node n, final int stepper, final JavaScriptObject cb, final JavaScriptObject errorcb, final JavaScriptObject then, boolean first) {
		if (n instanceof NodeNum) {
			NodeNum numNode = (NodeNum) n;			
			write(numNode.getValue(), cb);
		} else if (n instanceof NodeBool) {
			NodeBool boolNode = (NodeBool) n;
			write(Boolean.toString(boolNode.getBoolean()), cb);
		} else if (n instanceof NodeString) {
			NodeString stringNode = (NodeString) n;
			write("\"" + stringNode.getStringContent() + "\"" , cb);
		} else if (n instanceof NodeFunction) {
			write("(function)", cb);
		} else if (n instanceof NodeNil) {
			write("nil", cb);
		} else if (n instanceof NodePair) {				
			Scheduler.get().scheduleFixedDelay(new RepeatingCommand () {				
		    	Stack<Node> s = new Stack<Node>() {
					private static final long serialVersionUID = 1L;
					{push(n);push(new StringPrintNode("["));}};
				final int thisRun = run;
					
			    @Override
			    public boolean execute() {
			    	if (cancelRun == thisRun || s.empty()) {
			    		if (s.empty()) call(then);
			    		return false;
			    	}
					Node curWork = s.pop();						
					
					if (curWork instanceof NodePair) {
						try {
							Node left = ReductionMachine.reduce(((NodePair)curWork).getLeft());
							Node right = ReductionMachine.reduce(((NodePair)curWork).getRight());		
							if (!(right instanceof NodeNil) && right instanceof NodePair) {
								s.push(right);
								s.push(new StringPrintNode(","));
							} else if (!(right instanceof NodeNil) && !(right instanceof NodePair)){
								write("Tail of pair is not of type pair.", errorcb);
							} else {
								s.push(new StringPrintNode("]"));
							}
							s.push(left);	
							if (left instanceof NodePair) {
								s.push(new StringPrintNode("["));
							}
						} catch(ReduceException e) {
							write(e.getMessage(), errorcb);
						}
					} else if (curWork instanceof StringPrintNode) {
						write(curWork.getSymbol(), cb);
					} else {
						printHelper(curWork, stepper, cb, errorcb, then, false);
					}					
					return true;
			    }
			}, stepper);		
		}
		
		if (first && !(n instanceof NodePair)) {
			call(then);
		}
	}

	/*
	 * prints a printable reduction-machine result to the native JS output callback
	 */
	public void print(final Node n, final int stepper, final JavaScriptObject cb, final JavaScriptObject errorcb, final JavaScriptObject then) {
		// cancel previous run
		cancel();
		run++;
		printHelper(n, stepper, cb, errorcb, then, true);
	}
	
	final native int write(String str, JavaScriptObject cb) /*-{
	    return cb(str);
	}-*/;
	
	final native int call(JavaScriptObject cb) /*-{
	    cb();
	}-*/;
}