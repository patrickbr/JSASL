package com.patrickbr.saslcompiler.client.nodes;

public class NodeOr extends Node {	
	public NodeOr() {
		super("or");		
	}

	public boolean isConstant() {
		return true;
	}
	
	public boolean isPrimitiveBool() {
		return true;
	}
}