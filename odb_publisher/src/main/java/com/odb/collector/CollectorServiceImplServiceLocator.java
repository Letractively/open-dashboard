/**
 * CollectorServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public class CollectorServiceImplServiceLocator extends org.apache.axis.client.Service implements com.odb.collector.CollectorServiceImplService {

    public CollectorServiceImplServiceLocator() {
    }


    public CollectorServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CollectorServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CollectorServiceImplPort
    private java.lang.String CollectorServiceImplPort_address = "http://localhost:8080/odbserver/collector/collectorService";

    public java.lang.String getCollectorServiceImplPortAddress() {
        return CollectorServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CollectorServiceImplPortWSDDServiceName = "CollectorServiceImplPort";

    public java.lang.String getCollectorServiceImplPortWSDDServiceName() {
        return CollectorServiceImplPortWSDDServiceName;
    }

    public void setCollectorServiceImplPortWSDDServiceName(java.lang.String name) {
        CollectorServiceImplPortWSDDServiceName = name;
    }

    public com.odb.collector.CollectorService getCollectorServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CollectorServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCollectorServiceImplPort(endpoint);
    }

    public com.odb.collector.CollectorService getCollectorServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.odb.collector.CollectorServiceImplServiceSoapBindingStub _stub = new com.odb.collector.CollectorServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCollectorServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCollectorServiceImplPortEndpointAddress(java.lang.String address) {
        CollectorServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.odb.collector.CollectorService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.odb.collector.CollectorServiceImplServiceSoapBindingStub _stub = new com.odb.collector.CollectorServiceImplServiceSoapBindingStub(new java.net.URL(CollectorServiceImplPort_address), this);
                _stub.setPortName(getCollectorServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CollectorServiceImplPort".equals(inputPortName)) {
            return getCollectorServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://collector.odb.com/", "CollectorServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://collector.odb.com/", "CollectorServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CollectorServiceImplPort".equals(portName)) {
            setCollectorServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
