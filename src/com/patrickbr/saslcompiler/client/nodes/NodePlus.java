package com.patrickbr.saslcompiler.client.nodes;

public class NodePlus extends Node {
	public NodePlus() {		
		super("plus");				
	}	

	public boolean isConstant() {
		return true;
	}

	public boolean isPrimitiveNum() {
		return true;
	}
}