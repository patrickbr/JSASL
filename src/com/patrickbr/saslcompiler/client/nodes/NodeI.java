package com.patrickbr.saslcompiler.client.nodes;

public class NodeI extends Node {
	public NodeI() {		
		super("I");		
	}

	public boolean isConstant() {
		return true;
	}
}