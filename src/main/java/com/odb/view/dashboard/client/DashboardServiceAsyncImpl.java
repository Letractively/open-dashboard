package com.odb.view.dashboard.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.odb.view.dashboard.client.dto.DataSourceInfo;
import com.odb.view.dashboard.client.dto.PublisherInfo;
import com.odb.view.dashboard.client.dto.ViewSettings;

public class DashboardServiceAsyncImpl implements DashboardServiceAsync {

	public void getDataUpdate(String dataSourceId, String graphID, AsyncCallback<DataVO> callback) {
		// TODO Auto-generated method stub

	}

	public void getCurrentViewSettings(AsyncCallback<ViewSettings> callback) {
		// TODO Auto-generated method stub

	}
	public void getPublisherInfo(AsyncCallback<ArrayList<PublisherInfo>> callback) {
		// TODO Auto-generated method stub
		
	}

	public void getDataSources(String publisherID, AsyncCallback<ArrayList<DataSourceInfo>> callback) {
		// TODO Auto-generated method stub
		
	}

	public void getDataSourceAllDetails(String dataSourceID, AsyncCallback<HashMap<String, Serializable>> callback) {
		// TODO Auto-generated method stub
		
	}

}
