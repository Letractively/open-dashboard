package com.odb.collector;

public class CollectorServiceProxy implements com.odb.collector.CollectorService {
  private String _endpoint = null;
  private com.odb.collector.CollectorService collectorService = null;
  
  public CollectorServiceProxy() {
    _initCollectorServiceProxy();
  }
  
  public CollectorServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCollectorServiceProxy();
  }
  
  private void _initCollectorServiceProxy() {
    try {
      collectorService = (new com.odb.collector.CollectorServiceImplServiceLocator()).getCollectorServiceImplPort();
      if (collectorService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)collectorService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)collectorService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (collectorService != null)
      ((javax.xml.rpc.Stub)collectorService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.odb.collector.CollectorService getCollectorService() {
    if (collectorService == null)
      _initCollectorServiceProxy();
    return collectorService;
  }
  
  public void addDataSeries(java.lang.String arg0, java.lang.String arg1, com.odb.collector.UserDataWrapper arg2) throws java.rmi.RemoteException{
    if (collectorService == null)
      _initCollectorServiceProxy();
    collectorService.addDataSeries(arg0, arg1, arg2);
  }
  
  
}