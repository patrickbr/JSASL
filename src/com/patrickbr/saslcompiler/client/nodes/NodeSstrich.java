package com.patrickbr.saslcompiler.client.nodes;

public class NodeSstrich extends Node {	
	public NodeSstrich() {
		super("S'");		
	}
	
	public boolean isConstant() {
		return true;
	}	
}