package com.patrickbr.saslcompiler.client.nodes;

public class NodeAnd extends Node {	
	public NodeAnd() {
		super("and");			
	}
	
	public boolean isConstant() {
		return true;
	}
	
	public boolean isPrimitiveBool() {
		return true;
	}	
}