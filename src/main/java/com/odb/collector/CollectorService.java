package com.odb.collector;

import javax.jws.WebService;

import com.odb.core.dao.dto.DataSourceInfo;
import com.odb.core.service.DataSourceConfiguration;

/**
 * The Interface CollectorService.
 * 
 * this class have the main collector web services
 */
@WebService
public interface CollectorService extends java.rmi.Remote {

	
	/**
	 * Adds the data series.
	 *
	 * @param pubID the publisher id
	 * @param dsID the data source id
	 * @param userData the user data
	 * @throws RemoteException the remote exception
	 */
	void addDataSeries(String pubID, String dsID, UserDataWrapper userData) throws java.rmi.RemoteException;
	
}
