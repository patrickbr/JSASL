package com.patrickbr.saslcompiler.client.nodes;

public class NodeGte extends Node {		
	public NodeGte() {		
		super("gte");
	}
	
	public boolean isConstant() {
		return true;
	}
		
	public boolean isPrimitiveNum() {
		return true;
	}
}