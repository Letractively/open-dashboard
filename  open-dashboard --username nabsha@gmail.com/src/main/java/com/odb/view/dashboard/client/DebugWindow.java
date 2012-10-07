/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;

public class DebugWindow implements IsWidget{

	private final TextArea debugLog = new TextArea();
	private static DebugWindow dbgWindow = null;
	private DebugWindow() {
		
	}
	
	public static DebugWindow getInstance() {
		if (dbgWindow == null) {
			dbgWindow = new DebugWindow();
		}
		return dbgWindow;
	}
	public void debug(String msg) {
		debugLog.setText(msg + "\n" + debugLog.getText());
	}
	public Widget asWidget() {
		final ContentPanel panel = new FramedPanel();
		panel.setCollapsible(true);
		new Resizable(panel);

		panel.getElement().getStyle().setMargin(2, Unit.PX);
		// set a header of the panel
		panel.setHeadingText("Debug Messages");
		panel.setBodyBorder(true);
		panel.setBodyStyleName("white-bg");
		debugLog.setVisibleLines(10);
		debugLog.setWidth("100%");
		debugLog.setHeight("10%");
		// debugLog.setVisibleLines(40);
		debugLog.setReadOnly(true);
		ScrollPanel debugScrollable = new ScrollPanel(debugLog);
		panel.add(debugScrollable);
		return debugScrollable;
	}

}
