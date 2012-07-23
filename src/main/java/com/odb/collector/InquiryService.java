package com.odb.collector;

import java.util.ArrayList;

import javax.jws.WebService;

import com.odb.core.dao.dto.DataSourceInfo;
import com.odb.core.dao.dto.PublisherInfo;
import com.odb.core.service.DataSourceConfiguration;

/**
 * The Interface CollectorService.
 * 
 * this class have the main collector web services
 */
@WebService
public interface InquiryService extends java.rmi.Remote {

		
	/**
	 * Gets the data source info.
	 *
	 * @param datasourceId the datasource id
	 * @return the data source info
	 * @throws RemoteException the remote exception
	 */
	DataSourceInfo getDataSourceInfo(String datasourceId) throws java.rmi.RemoteException;
	PublisherInfo getPublisherInfo(String publisherID) throws java.rmi.RemoteException;
	ArrayList<DataSourceConfiguration> getAllDataSourceByPublisherID(String publisherID) throws java.rmi.RemoteException;
}