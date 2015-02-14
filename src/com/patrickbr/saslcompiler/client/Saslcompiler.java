package com.patrickbr.saslcompiler.client;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExporterUtil;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.patrickbr.saslcompiler.client.machines.Printer;

@Export
public class Saslcompiler implements EntryPoint {
	
	public void onModuleLoad() {
		ExporterUtil.exportAll();
		GWT.create(Printer.class);
		onLoad();
	}
	
	private native void onLoad() /*-{
		if ($wnd.saslInit) $wnd.saslInit();
	}-*/;
}