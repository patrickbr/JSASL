package com.patrickbr.saslcompiler.client.nodes;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;

@ExportPackage("sasl")
@Export
public class NodeFunction extends Node {	
	public NodeFunction() {
		super("function");		
	}

	public boolean isPrintable() {
		return true;
	}	
}