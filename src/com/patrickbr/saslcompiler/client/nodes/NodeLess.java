package com.patrickbr.saslcompiler.client.nodes;

public class NodeLess extends Node {	
	public NodeLess() {
		super("less");		
	}
	
	public boolean isConstant() {
		return true;
	}
	
	public boolean isPrimitiveNum() {
		return true;
	}	
}