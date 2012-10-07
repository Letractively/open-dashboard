/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.server;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.odb.core.SubscriberDataSource;
import com.odb.core.dao.dto.DataSourceAxisDetailInfo;
import com.odb.core.dao.dto.DataSourceInfo;
import com.odb.core.dao.dto.DataSourceSeries;
import com.odb.core.dao.dto.PublisherInfo;
import com.odb.core.dao.dto.SubscriberInfo;
import com.odb.core.dao.dto.ViewConfiguration;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.core.service.OpenDashBoard;
import com.odb.view.dashboard.client.DashboardService;
import com.odb.view.dashboard.client.DataVO;
import com.odb.view.dashboard.client.TimeSeriesDataVO;
import com.odb.view.dashboard.client.dto.DataSourceAxisInfo;
import com.odb.view.dashboard.client.dto.LiveChartVO;
import com.odb.view.dashboard.client.dto.ViewConfig;
import com.odb.view.dashboard.client.dto.ViewSettings;
import com.odb.view.dashboard.client.exceptions.FetchDataSourceException;
import com.odb.view.dashboard.client.exceptions.GraphNotAvailableException;
import com.odb.view.dashboard.client.exceptions.ViewSettingNotConsistentException;
import com.odb.view.util.Utilities;

/**
 * The server side implementation of the RPC service.<br>
 * 
 * this class is of type RemoteServiceServlet -i.e it is a servlet at the end-
 * and hence, it is initialised by the
 * {@link #init(javax.servlet.ServletConfig)} method.<br>
 * by ServletConfig object we can get a reference to Spring
 * WebApplicationContext, and hence, the OpenDashBoardCore service.
 */
@SuppressWarnings("serial")
public class DashboardServiceImpl extends RemoteServiceServlet implements DashboardService {

	/** The log. */
	private static Logger log = Logger.getLogger(DashboardServiceImpl.class);

	/** The odb core. */
	OpenDashBoard odbCore;

	public void init(javax.servlet.ServletConfig config) throws javax.servlet.ServletException {
		super.init(config);
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		odbCore = (OpenDashBoard) context.getBean("OpenDashBoardCore");
	};

	public ArrayList<DataVO> getDataUpdate(String dataSourceId, String graphID, int seriesCount, int seriesSetCount) throws GraphNotAvailableException {
		ArrayList<DataVO> dataSetList = new ArrayList<DataVO>();
		int rowNum = seriesCount * seriesSetCount;

		List<DataSourceSeries> series = odbCore.getLatestSeriesData(dataSourceId, rowNum);
		for (int i = 0; i < series.size(); i += seriesCount) {
			TimeSeriesDataVO dataSet = new TimeSeriesDataVO(series.get(i).getDateTime());
			for (int j = 0; j < seriesCount; j++) {
				int idx = i+j;
				dataSet.put(series.get(idx).getSeriesIndex(), series.get(idx).getSeriesIndexSeqVal());
			}
			dataSetList.add(dataSet);
		}
		return dataSetList;
		// throw new GraphNotAvailableException();
	}

	public DataSourceConfiguration getDataSourceAllDetails(String dataSourceID) {

		// HashMap<String, Serializable> dsAllDetails = new
		// java.util.HashMap<String, Serializable>();
		DataSourceConfiguration dsConfig = new DataSourceConfiguration();
		try {
			dsConfig = odbCore.getDataSourceConfigurationBy(dataSourceID);
		} catch (SQLException e) {
			log.error("Failed while getting data source details for dsID=" + dataSourceID + e);
		}

		return dsConfig;
	}

	public ArrayList<DataSourceConfiguration> getCurrentSubscriptions() {
		ArrayList<DataSourceConfiguration> dsConfigList = null;
		SubscriberInfo subscriberInfo = (SubscriberInfo) getThreadLocalRequest().getSession().getAttribute("subscriberInfo");
		String subscriberID = Utilities.getClientSubscriberInfo(subscriberInfo).getSubscriberID();
		try {
			ArrayList<DataSourceInfo> subDS = odbCore.getAllDataSourceBySubscriber(subscriberID);
			dsConfigList = new ArrayList<DataSourceConfiguration>(subDS.size());
			for (DataSourceInfo ds : subDS) {
				DataSourceConfiguration dsConfig = odbCore.getDataSourceConfigurationBy(ds.getDataSourceID());
				dsConfigList.add(dsConfig);
			}

		} catch (SQLException e) {
			log.error("Getting Current Subscription for the subscriber failed: subID:" + subscriberID + e);
		}

		return dsConfigList;
	}

	public ArrayList<com.odb.view.dashboard.client.dto.PublisherInfo> getPublisherInfo() throws FetchDataSourceException {
		ArrayList<com.odb.view.dashboard.client.dto.PublisherInfo> publisherInfoList = new ArrayList<com.odb.view.dashboard.client.dto.PublisherInfo>();
		try {
			ArrayList<PublisherInfo> pubInfo = odbCore.getAllPublishers();
			for (PublisherInfo pInfo : pubInfo) {
				com.odb.view.dashboard.client.dto.PublisherInfo pi = new com.odb.view.dashboard.client.dto.PublisherInfo();
				pi.setPublisherID(pInfo.getPublisherID());
				pi.setPublisherName(pInfo.getPublisherName());
				publisherInfoList.add(pi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return publisherInfoList;
	}

	public ArrayList<com.odb.view.dashboard.client.dto.DataSourceInfo> getDataSources(String publisherID) {
		ArrayList<com.odb.view.dashboard.client.dto.DataSourceInfo> dataSourceList = new ArrayList<com.odb.view.dashboard.client.dto.DataSourceInfo>();
		try {
			ArrayList<DataSourceInfo> dsInfo = odbCore.getAllDataSourceByPublisher(publisherID);
			for (DataSourceInfo dInfo : dsInfo) {
				com.odb.view.dashboard.client.dto.DataSourceInfo dataSource = new com.odb.view.dashboard.client.dto.DataSourceInfo();
				dataSource.setDataSourceID(dInfo.getDataSourceID());
				dataSource.setDataSourceName(dInfo.getDataSourceName());
				dataSource.setPublisherID(dInfo.getPublisherID());
				dataSource.setSeriesCount(dInfo.getSeriesCount());
				dataSource.setTimeoutInterval(dInfo.getTimeoutInterval());
				dataSourceList.add(dataSource);
			}
		} catch (SQLException e) {
			log.error("Fetching DataSourceInfo failed... ");
		}
		return dataSourceList;
	}

	public void addSubscription(String dsID, String graphID) {
		SubscriberInfo subscriberInfo = (SubscriberInfo) getThreadLocalRequest().getSession().getAttribute("subscriberInfo");
		odbCore.addSubscription(subscriberInfo.getSubscriberID(), dsID, graphID, subscriberInfo.getSubscriberID() + "_"+dsID);
	}

}
