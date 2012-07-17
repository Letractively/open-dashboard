/**
 * RegisterService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

import javax.jws.WebService;

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
    
}
