package com.patrickbr.saslcompiler.client.nodes;

public class NodeHd extends Node {	
	public NodeHd() {
		super("hd");
	}
	
	public boolean isConstant() {
		return true;
	}	
}