package com.patrickbr.saslcompiler.client.nodes;

public class NodeTimeout extends Node {	
	public NodeTimeout() {		
		super("<timeout>");		
	}
	
	public boolean isConstant() {
		return true;
	}
	
	public boolean isPrintable() {
		return true;
	}

	public String getValue(){
		return "<timeout>";
	}
}