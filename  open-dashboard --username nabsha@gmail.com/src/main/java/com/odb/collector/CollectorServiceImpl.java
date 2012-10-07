/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.collector;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.odb.core.dao.dto.DataSourceInfo;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.core.service.OpenDashBoard;

/**
 * The Class CollectorServiceImpl.
 * 
 * this class is an implementation for the com.odb.collector.CollectorService endpointInterface
 */
@WebService(endpointInterface="com.odb.collector.CollectorService")
public class CollectorServiceImpl implements CollectorService {

	/** The log. */
	private static Logger log = Logger.getLogger(CollectorServiceImpl.class);
	
	/** The OpenDashBoard core service. */
	private OpenDashBoard odbCore;
	
	/**
	 * Sets the OpenDashBoard core service.
	 *
	 * @param odbCore the new odb core
	 */
	public void setOdbCore(OpenDashBoard odbCore) {
		this.odbCore = odbCore;
	}

	

	/* (non-Javadoc)
	 * @see com.odb.collector.CollectorService#addDataSeries(java.lang.String, java.lang.String, com.odb.collector.UserDataWrapper)
	 */
	public void addDataSeries(String pubID, String dsID,
			UserDataWrapper userData) throws RemoteException {
		try {
			odbCore.addDataSeries(pubID, dsID, userData.map);
		} catch (SQLException e) {
			log.error("error while adding Data Series for publisher ID: "+ pubID+" and data source Id:" + dsID, e);
		}
	}



}
