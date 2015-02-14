package com.patrickbr.saslcompiler.client.nodes;

public class NodeDiv extends Node {	
	public NodeDiv() {
		super("div");			
	}
		
	public boolean isConstant() {
		return true;
	}
		
	public boolean isPrimitiveNum() {
		return true;
	}	
}