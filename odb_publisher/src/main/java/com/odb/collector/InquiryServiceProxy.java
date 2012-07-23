package com.odb.collector;

public class InquiryServiceProxy implements com.odb.collector.InquiryService {
  private String _endpoint = null;
  private com.odb.collector.InquiryService inquiryService = null;
  
  public InquiryServiceProxy() {
    _initInquiryServiceProxy();
  }
  
  public InquiryServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initInquiryServiceProxy();
  }
  
  private void _initInquiryServiceProxy() {
    try {
      inquiryService = (new com.odb.collector.InquiryServiceImplServiceLocator()).getInquiryServiceImplPort();
      if (inquiryService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)inquiryService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)inquiryService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (inquiryService != null)
      ((javax.xml.rpc.Stub)inquiryService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.odb.collector.InquiryService getInquiryService() {
    if (inquiryService == null)
      _initInquiryServiceProxy();
    return inquiryService;
  }
  
  public com.odb.collector.PublisherInfo getPublisherInfo(java.lang.String arg0) throws java.rmi.RemoteException{
    if (inquiryService == null)
      _initInquiryServiceProxy();
    return inquiryService.getPublisherInfo(arg0);
  }
  
  public com.odb.collector.DataSourceConfiguration[] getAllDataSourceByPublisherID(java.lang.String arg0) throws java.rmi.RemoteException{
    if (inquiryService == null)
      _initInquiryServiceProxy();
    return inquiryService.getAllDataSourceByPublisherID(arg0);
  }
  
  public com.odb.collector.DataSourceInfo getDataSourceInfo(java.lang.String arg0) throws java.rmi.RemoteException{
    if (inquiryService == null)
      _initInquiryServiceProxy();
    return inquiryService.getDataSourceInfo(arg0);
  }
  
  
}