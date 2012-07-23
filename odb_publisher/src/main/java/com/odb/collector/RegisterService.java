/**
 * RegisterService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public interface RegisterService extends java.rmi.Remote {
    public java.lang.String registerDataSource(com.odb.collector.DataSourceConfiguration arg0) throws java.rmi.RemoteException;
    public java.lang.String registerPublisher(java.lang.String arg0) throws java.rmi.RemoteException;
}
