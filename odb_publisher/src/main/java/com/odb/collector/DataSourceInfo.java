/**
 * DataSourceInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public class DataSourceInfo  implements java.io.Serializable {
    private java.lang.String dataSourceID;

    private java.lang.String dataSourceName;

    private java.lang.String publisherID;

    private int seriesCount;

    private java.lang.Long timeoutInterval;

    public DataSourceInfo() {
    }

    public DataSourceInfo(
           java.lang.String dataSourceID,
           java.lang.String dataSourceName,
           java.lang.String publisherID,
           int seriesCount,
           java.lang.Long timeoutInterval) {
           this.dataSourceID = dataSourceID;
           this.dataSourceName = dataSourceName;
           this.publisherID = publisherID;
           this.seriesCount = seriesCount;
           this.timeoutInterval = timeoutInterval;
    }


    /**
     * Gets the dataSourceID value for this DataSourceInfo.
     * 
     * @return dataSourceID
     */
    public java.lang.String getDataSourceID() {
        return dataSourceID;
    }


    /**
     * Sets the dataSourceID value for this DataSourceInfo.
     * 
     * @param dataSourceID
     */
    public void setDataSourceID(java.lang.String dataSourceID) {
        this.dataSourceID = dataSourceID;
    }


    /**
     * Gets the dataSourceName value for this DataSourceInfo.
     * 
     * @return dataSourceName
     */
    public java.lang.String getDataSourceName() {
        return dataSourceName;
    }


    /**
     * Sets the dataSourceName value for this DataSourceInfo.
     * 
     * @param dataSourceName
     */
    public void setDataSourceName(java.lang.String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }


    /**
     * Gets the publisherID value for this DataSourceInfo.
     * 
     * @return publisherID
     */
    public java.lang.String getPublisherID() {
        return publisherID;
    }


    /**
     * Sets the publisherID value for this DataSourceInfo.
     * 
     * @param publisherID
     */
    public void setPublisherID(java.lang.String publisherID) {
        this.publisherID = publisherID;
    }


    /**
     * Gets the seriesCount value for this DataSourceInfo.
     * 
     * @return seriesCount
     */
    public int getSeriesCount() {
        return seriesCount;
    }


    /**
     * Sets the seriesCount value for this DataSourceInfo.
     * 
     * @param seriesCount
     */
    public void setSeriesCount(int seriesCount) {
        this.seriesCount = seriesCount;
    }


    /**
     * Gets the timeoutInterval value for this DataSourceInfo.
     * 
     * @return timeoutInterval
     */
    public java.lang.Long getTimeoutInterval() {
        return timeoutInterval;
    }


    /**
     * Sets the timeoutInterval value for this DataSourceInfo.
     * 
     * @param timeoutInterval
     */
    public void setTimeoutInterval(java.lang.Long timeoutInterval) {
        this.timeoutInterval = timeoutInterval;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSourceInfo)) return false;
        DataSourceInfo other = (DataSourceInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataSourceID==null && other.getDataSourceID()==null) || 
             (this.dataSourceID!=null &&
              this.dataSourceID.equals(other.getDataSourceID()))) &&
            ((this.dataSourceName==null && other.getDataSourceName()==null) || 
             (this.dataSourceName!=null &&
              this.dataSourceName.equals(other.getDataSourceName()))) &&
            ((this.publisherID==null && other.getPublisherID()==null) || 
             (this.publisherID!=null &&
              this.publisherID.equals(other.getPublisherID()))) &&
            this.seriesCount == other.getSeriesCount() &&
            ((this.timeoutInterval==null && other.getTimeoutInterval()==null) || 
             (this.timeoutInterval!=null &&
              this.timeoutInterval.equals(other.getTimeoutInterval())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDataSourceID() != null) {
            _hashCode += getDataSourceID().hashCode();
        }
        if (getDataSourceName() != null) {
            _hashCode += getDataSourceName().hashCode();
        }
        if (getPublisherID() != null) {
            _hashCode += getPublisherID().hashCode();
        }
        _hashCode += getSeriesCount();
        if (getTimeoutInterval() != null) {
            _hashCode += getTimeoutInterval().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataSourceInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://collector.odb.com/", "dataSourceInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSourceID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataSourceID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSourceName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataSourceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisherID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publisherID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seriesCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seriesCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeoutInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeoutInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
