package com.patrickbr.saslcompiler.client.nodes;

public class NodeCstrich extends Node {	
	public NodeCstrich() {
		super("C'");		
	}
	
	public boolean isConstant() {
		return true;
	}
}