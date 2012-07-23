/**
 * RegisterServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public class RegisterServiceImplServiceLocator extends org.apache.axis.client.Service implements com.odb.collector.RegisterServiceImplService {

    public RegisterServiceImplServiceLocator() {
    }


    public RegisterServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RegisterServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RegisterServiceImplPort
    private java.lang.String RegisterServiceImplPort_address = "http://localhost:8080/odbserver/collector/registerService";

    public java.lang.String getRegisterServiceImplPortAddress() {
        return RegisterServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RegisterServiceImplPortWSDDServiceName = "RegisterServiceImplPort";

    public java.lang.String getRegisterServiceImplPortWSDDServiceName() {
        return RegisterServiceImplPortWSDDServiceName;
    }

    public void setRegisterServiceImplPortWSDDServiceName(java.lang.String name) {
        RegisterServiceImplPortWSDDServiceName = name;
    }

    public com.odb.collector.RegisterService getRegisterServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RegisterServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRegisterServiceImplPort(endpoint);
    }

    public com.odb.collector.RegisterService getRegisterServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.odb.collector.RegisterServiceImplServiceSoapBindingStub _stub = new com.odb.collector.RegisterServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getRegisterServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRegisterServiceImplPortEndpointAddress(java.lang.String address) {
        RegisterServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.odb.collector.RegisterService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.odb.collector.RegisterServiceImplServiceSoapBindingStub _stub = new com.odb.collector.RegisterServiceImplServiceSoapBindingStub(new java.net.URL(RegisterServiceImplPort_address), this);
                _stub.setPortName(getRegisterServiceImplPortWSDDServiceName());
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
        if ("RegisterServiceImplPort".equals(inputPortName)) {
            return getRegisterServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://collector.odb.com/", "RegisterServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://collector.odb.com/", "RegisterServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RegisterServiceImplPort".equals(portName)) {
            setRegisterServiceImplPortEndpointAddress(address);
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
