/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.collector;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.odb.core.dao.dto.DataSourceInfo;
import com.odb.core.dao.dto.PublisherInfo;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.core.service.OpenDashBoard;

/**
 * The Class CollectorServiceImpl.
 * 
 * this class is an implementation for the com.odb.collector.CollectorService
 * endpointInterface
 */
@WebService(endpointInterface = "com.odb.collector.InquiryService")
public class InquiryServiceImpl implements InquiryService {

	/** The log. */
	private static Logger log = Logger.getLogger(InquiryServiceImpl.class);

	/** The OpenDashBoard core service. */
	private OpenDashBoard odbCore;

	/**
	 * Sets the OpenDashBoard core service.
	 * 
	 * @param odbCore
	 *            the new odb core
	 */
	public void setOdbCore(OpenDashBoard odbCore) {
		this.odbCore = odbCore;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.odb.collector.CollectorService#getDataSourceInfo(java.lang.String)
	 */
	public DataSourceInfo getDataSourceInfo(String datasourceId) throws RemoteException {
		try {
			return odbCore.getDataSourceInfo(datasourceId);
		} catch (SQLException e) {
			log.error("error while get DataSourceInfo for datasourceId: " + datasourceId, e);
		}
		return null;
	}

	public PublisherInfo getPublisherInfo(String publisherID) throws RemoteException {
		PublisherInfo pInfo = null;
		try {
			pInfo = odbCore.getPublisher(publisherID);
		} catch (SQLException e) {
			log.error("error while getPublisherInfo for publisherID: " + publisherID, e);
		}
		return pInfo;
	}

	public ArrayList<DataSourceConfiguration> getAllDataSourceByPublisherID(String publisherID) throws RemoteException {
		ArrayList<DataSourceConfiguration> dsConfigList = null;
		try {
			ArrayList<DataSourceInfo> dsInfoList = odbCore.getAllDataSourceByPublisher(publisherID);
			dsConfigList = new ArrayList<DataSourceConfiguration>();

			for (DataSourceInfo dsInfo : dsInfoList) {
				DataSourceConfiguration dsConfig = new DataSourceConfiguration();
				dsConfig = odbCore.getDataSourceConfigurationBy(dsInfo.getDataSourceID());
				dsConfigList.add(dsConfig);
			}
			return dsConfigList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
