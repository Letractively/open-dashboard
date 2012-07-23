package com.odb.collector;

public class RegisterServiceProxy implements com.odb.collector.RegisterService {
  private String _endpoint = null;
  private com.odb.collector.RegisterService registerService = null;
  
  public RegisterServiceProxy() {
    _initRegisterServiceProxy();
  }
  
  public RegisterServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegisterServiceProxy();
  }
  
  private void _initRegisterServiceProxy() {
    try {
      registerService = (new com.odb.collector.RegisterServiceImplServiceLocator()).getRegisterServiceImplPort();
      if (registerService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)registerService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)registerService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (registerService != null)
      ((javax.xml.rpc.Stub)registerService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.odb.collector.RegisterService getRegisterService() {
    if (registerService == null)
      _initRegisterServiceProxy();
    return registerService;
  }
  
  public java.lang.String registerDataSource(com.odb.collector.DataSourceConfiguration arg0) throws java.rmi.RemoteException{
    if (registerService == null)
      _initRegisterServiceProxy();
    return registerService.registerDataSource(arg0);
  }
  
  public java.lang.String registerPublisher(java.lang.String arg0) throws java.rmi.RemoteException{
    if (registerService == null)
      _initRegisterServiceProxy();
    return registerService.registerPublisher(arg0);
  }
  
  
}