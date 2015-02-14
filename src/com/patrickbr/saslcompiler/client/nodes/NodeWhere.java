package com.patrickbr.saslcompiler.client.nodes;

import java.util.ArrayList;

public class NodeWhere extends Node {	
	public ArrayList<Definition> wheres=new ArrayList<Definition>();
	
	public NodeWhere() {		
		super("where");		
	}	
}