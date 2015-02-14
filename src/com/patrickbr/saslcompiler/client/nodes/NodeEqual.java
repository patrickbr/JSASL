package com.patrickbr.saslcompiler.client.nodes;

public class NodeEqual extends Node {
	public NodeEqual() {		
		super("equal");		
	}
	
	public boolean isConstant() {
		return true;
	}	
}