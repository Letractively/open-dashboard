package com.odb.collector;

import javax.jws.WebService;

import com.odb.core.DataSourceInfo;
import com.odb.core.service.DataSourceConfiguration;

/**
 * The Interface CollectorService.
 * 
 * this class have the main collector web services
 */
@WebService
public interface CollectorService extends java.rmi.Remote {

	/**
	 * Register data source.
	 *
	 * @param pubID the publisher id
	 * @param dsConfig the data source configuration
	 * like how many axis and the timeout interval and so on.
	 * @return the Data source ID
	 * @throws RemoteException the remote exception
	 */
	String registerDataSource(String pubID, DataSourceConfiguration dsConfig) throws java.rmi.RemoteException;
	
	/**
	 * Adds the data series.
	 *
	 * @param pubID the publisher id
	 * @param dsID the data source id
	 * @param userData the user data
	 * @throws RemoteException the remote exception
	 */
	void addDataSeries(String pubID, String dsID, UserDataWrapper userData) throws java.rmi.RemoteException;
	
	/**
	 * Gets the data source info.
	 *
	 * @param datasourceId the datasource id
	 * @return the data source info
	 * @throws RemoteException the remote exception
	 */
	DataSourceInfo getDataSourceInfo(String datasourceId) throws java.rmi.RemoteException;
}
