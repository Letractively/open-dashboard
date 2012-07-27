package com.odb.view.dashboard.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.view.dashboard.client.dto.DataSourceInfo;
import com.odb.view.dashboard.client.dto.PublisherInfo;
import com.odb.view.dashboard.client.dto.ViewSettings;

public class DashboardServiceAsyncImpl implements DashboardServiceAsync {

	public void getDataUpdate(String dataSourceId, String graphID, int seriesCount, int seriesSetCount, AsyncCallback<ArrayList<DataVO>> callback) {
		// TODO Auto-generated method stub

	}

//	public void getCurrentViewSettings(AsyncCallback<ViewSettings> callback) {
//		// TODO Auto-generated method stub
//
//	}
	public void getPublisherInfo(AsyncCallback<ArrayList<PublisherInfo>> callback) {
		// TODO Auto-generated method stub
		
	}

	public void getDataSources(String publisherID, AsyncCallback<ArrayList<DataSourceInfo>> callback) {
		// TODO Auto-generated method stub
		
	}

	public void getDataSourceAllDetails(String dataSourceID, AsyncCallback<DataSourceConfiguration> callback) {
		// TODO Auto-generated method stub
		
	}

	public void getCurrentSubscriptions(AsyncCallback<ArrayList<DataSourceConfiguration>> asyncCallback) {
		// TODO Auto-generated method stub
		
	}

}
