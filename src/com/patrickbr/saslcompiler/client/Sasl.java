package com.patrickbr.saslcompiler.client;

import java.util.ArrayList;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.Exportable;

import com.google.gwt.core.client.JavaScriptObject;
import com.patrickbr.saslcompiler.client.exceptions.ParseException;
import com.patrickbr.saslcompiler.client.exceptions.ReduceException;
import com.patrickbr.saslcompiler.client.machines.Compiler;
import com.patrickbr.saslcompiler.client.machines.Lexer;
import com.patrickbr.saslcompiler.client.machines.Parser;
import com.patrickbr.saslcompiler.client.machines.ReductionMachine;
import com.patrickbr.saslcompiler.client.nodes.Definition;
import com.patrickbr.saslcompiler.client.nodes.Node;

@ExportPackage("sasl")
@Export("Sasl")
public class Sasl implements Exportable {
	
	public Node compileAndReduce(String code, JavaScriptObject errorcb) {
		try {
			Lexer lex = new Lexer(code);
			Node cTree = compile(parse(lex));
			return reduce(cTree);	
		} catch(ParseException e) {
			call(errorcb, e.getMessage());
		} catch(ReduceException e) {
			call(errorcb, e.getMessage());
		} catch(ClassCastException e) {
			call(errorcb, e.getMessage());
		}  		
		return null;
	}
	
	private ArrayList<Definition> parse(Lexer lex) throws ParseException {
		Parser p = new Parser();
		ArrayList<Definition> defs = p.parse(lex);

		return defs;
	}

	private Node reduce(Node cTree) throws ReduceException {
		Node reduced = ReductionMachine.reduce(cTree);
		return reduced;
	}

	private Node compile(ArrayList<Definition> defs) {
		Compiler c = new Compiler(defs);
		Node cTree=c.getCompiledTree();
		return cTree;
	}
	
	final native int call(JavaScriptObject cb, String str) /*-{
	    cb(str);
	}-*/;
}
