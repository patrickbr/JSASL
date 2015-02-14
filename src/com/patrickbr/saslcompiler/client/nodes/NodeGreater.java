package com.patrickbr.saslcompiler.client.nodes;

public class NodeGreater extends Node {	
	public NodeGreater() {		
		super("greater");		
	}
	
	public boolean isConstant() {
		return true;
	}
		
	public boolean isPrimitiveNum() {
		return true;
	}	
}