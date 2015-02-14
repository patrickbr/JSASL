package com.patrickbr.saslcompiler.client.nodes;

public class NodeLte extends Node {	
	public NodeLte() {
		super("lte");		
	}
		
	public boolean isConstant() {
		return true;
	}
	
	public boolean isPrimitiveNum() {
		return true;
	}	
}