package com.patrickbr.saslcompiler.client.nodes;

public class NodeMult extends Node {	
	
	public NodeMult() {		
		super("mult");		
	}

	public boolean isConstant() {
		return true;
	}
	
	public boolean isPrimitiveNum() {
		return true;
	}	
}