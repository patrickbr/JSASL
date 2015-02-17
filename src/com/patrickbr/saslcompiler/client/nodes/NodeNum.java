package com.patrickbr.saslcompiler.client.nodes;

public class NodeNum extends Node {
	long num;
	
	public NodeNum(long num) {		
		super("num");
		this.num=num;		
	}

	public boolean isConstant() {
		return true;
	}
	
	public boolean isPrintable() {
		return true;
	}
	
	public long getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getValue() {
		return Long.toString(num);
	}	
}