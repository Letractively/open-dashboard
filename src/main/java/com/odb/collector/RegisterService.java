/**
 * RegisterService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

import javax.jws.WebService;

import com.odb.core.service.DataSourceConfiguration;

/**
 * The Interface RegisterService.
 * 
 * used for publisher registration services.
 */
@WebService
public interface RegisterService extends java.rmi.Remote {
    
    /**
     * Register publisher.
     *
     * @param publisherName the publisher Name
     * @return the string publisher Id
     * @throws RemoteException the remote exception
     */
    public java.lang.String registerPublisher(java.lang.String publisherName) throws java.rmi.RemoteException;
    
	/**
	 * Register data source.
	 *
	 * @param pubID the publisher id
	 * @param dsConfig the data source configuration
	 * like how many axis and the timeout interval and so on.
	 * @return the Data source ID
	 * @throws RemoteException the remote exception
	 */
	String registerDataSource(DataSourceConfiguration dsConfig) throws java.rmi.RemoteException;
}
