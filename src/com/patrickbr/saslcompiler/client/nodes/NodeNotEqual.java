package com.patrickbr.saslcompiler.client.nodes;

public class NodeNotEqual extends Node {	
	public NodeNotEqual() {		
		super("notequal");		
	}
	
	public boolean isConstant() {
		return true;
	}
}