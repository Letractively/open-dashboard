/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client;

import java.util.ArrayList;

import org.icepush.gwt.client.PushEventListener;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.view.core.ActionProcessor;
import com.odb.view.dashboard.client.charts.ODBChart;

/**
 * Dashboard class is the GWT Entry point classes that define
 * <code>onModuleLoad()</code>. <br>
 * The class uses the {@link DashboardService#getCurrentViewSettings()} to load
 * the user preferences on load time and once the settings is loaded, it begins
 * to draw the {@link ODBChart} for each graph
 * <p>
 * The class registers a {@link PushEventListener} listener to listen to the
 * events fired by the
 * {@link ActionProcessor#firePushEventAction(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
 * . <br>
 * Once the event is fired, the
 * {@link DashboardService#getDataUpdate(String, String)} get called. and the
 * chart the updated with the {@link ODBChart#updateChartData(DataVO, Label)}
 * <p>
 * Note that this class is a part of the client package. And hence, it will be
 * converted to Javascript code in the "GWT compilation" process
 * <p>
 * 
 * see <a href="http://code.google.com/webtoolkit/doc/latest/tutorial/">GWT
 * Tutorial</a> And <a
 * href="http://wiki.icesoft.org/display/PUSH/GWT+Integration">ICEpush GWT
 * Integration</a>
 * 
 * 
 */
public class Dashboard implements EntryPoint {

	/** The dashboard RPC service. */
	private DashboardServiceAsync dashboardService = GWT.create(DashboardService.class);
	private static DebugWindow dbg = DebugWindow.getInstance();

	public void onModuleLoad() {

		final ClientUI ui = new ClientUI(dashboardService);
		dashboardService.getCurrentSubscriptions(new AsyncCallback<ArrayList<DataSourceConfiguration>>() {

			public void onFailure(Throwable caught) {
				dbg.debug("Failed in getCurrentSubscriptions " + caught);

			}

			public void onSuccess(ArrayList<DataSourceConfiguration> result) {
				for (DataSourceConfiguration dsConfig : result) {
					ui.addChart(dsConfig, 400, 300);
				}

			}
		});
	}
}
