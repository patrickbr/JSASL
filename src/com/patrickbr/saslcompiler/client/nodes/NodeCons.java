package com.patrickbr.saslcompiler.client.nodes;

public class NodeCons extends Node {	
	public NodeCons() {
		super("cons");		
	}
		
	public boolean isConstant() {
		return true;
	}
}