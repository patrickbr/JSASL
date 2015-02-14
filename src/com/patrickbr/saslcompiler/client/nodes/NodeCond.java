package com.patrickbr.saslcompiler.client.nodes;

public class NodeCond extends Node {	
	public NodeCond() {
		super("cond");		
	}
	
	public boolean isConstant() {
		return true;
	}
}