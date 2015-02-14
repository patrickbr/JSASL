package com.patrickbr.saslcompiler.client.nodes;

public class NodeNot extends Node {	
	public NodeNot() {		
		super("not");		
	}
	
	public boolean isConstant() {
		return true;
	}
}