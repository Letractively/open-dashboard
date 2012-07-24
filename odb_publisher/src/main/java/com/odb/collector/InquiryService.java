/**
 * InquiryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public interface InquiryService extends java.rmi.Remote {
    public com.odb.collector.PublisherInfo getPublisherInfo(java.lang.String arg0) throws java.rmi.RemoteException;
    public com.odb.collector.DataSourceConfiguration[] getAllDataSourceByPublisherID(java.lang.String arg0) throws java.rmi.RemoteException;
    public com.odb.collector.DataSourceInfo getDataSourceInfo(java.lang.String arg0) throws java.rmi.RemoteException;
}
