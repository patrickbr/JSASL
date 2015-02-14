package com.patrickbr.saslcompiler.client.nodes;

public class NodeS extends Node {	
	public NodeS() {		
		super("S");		
	}
	
	public boolean isConstant() {
		return true;
	}
}