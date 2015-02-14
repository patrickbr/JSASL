package com.patrickbr.saslcompiler.client.nodes;

public class NodeTl extends Node {	
	public NodeTl() {		
		super("tail");		
	}
	
	public boolean isConstant() {
		return true;
	}	
}