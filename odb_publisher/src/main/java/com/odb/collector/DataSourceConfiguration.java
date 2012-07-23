/**
 * DataSourceConfiguration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public class DataSourceConfiguration  implements java.io.Serializable {
    private java.lang.String dsName;

    private java.lang.Long dsTimeoutInterval;

    private java.lang.String publisherID;

    private int seriesCount;

    private com.odb.collector.AxisInfo[] xsInfo;

    public DataSourceConfiguration() {
    }

    public DataSourceConfiguration(
           java.lang.String dsName,
           java.lang.Long dsTimeoutInterval,
           java.lang.String publisherID,
           int seriesCount,
           com.odb.collector.AxisInfo[] xsInfo) {
           this.dsName = dsName;
           this.dsTimeoutInterval = dsTimeoutInterval;
           this.publisherID = publisherID;
           this.seriesCount = seriesCount;
           this.xsInfo = xsInfo;
    }


    /**
     * Gets the dsName value for this DataSourceConfiguration.
     * 
     * @return dsName
     */
    public java.lang.String getDsName() {
        return dsName;
    }


    /**
     * Sets the dsName value for this DataSourceConfiguration.
     * 
     * @param dsName
     */
    public void setDsName(java.lang.String dsName) {
        this.dsName = dsName;
    }


    /**
     * Gets the dsTimeoutInterval value for this DataSourceConfiguration.
     * 
     * @return dsTimeoutInterval
     */
    public java.lang.Long getDsTimeoutInterval() {
        return dsTimeoutInterval;
    }


    /**
     * Sets the dsTimeoutInterval value for this DataSourceConfiguration.
     * 
     * @param dsTimeoutInterval
     */
    public void setDsTimeoutInterval(java.lang.Long dsTimeoutInterval) {
        this.dsTimeoutInterval = dsTimeoutInterval;
    }


    /**
     * Gets the publisherID value for this DataSourceConfiguration.
     * 
     * @return publisherID
     */
    public java.lang.String getPublisherID() {
        return publisherID;
    }


    /**
     * Sets the publisherID value for this DataSourceConfiguration.
     * 
     * @param publisherID
     */
    public void setPublisherID(java.lang.String publisherID) {
        this.publisherID = publisherID;
    }


    /**
     * Gets the seriesCount value for this DataSourceConfiguration.
     * 
     * @return seriesCount
     */
    public int getSeriesCount() {
        return seriesCount;
    }


    /**
     * Sets the seriesCount value for this DataSourceConfiguration.
     * 
     * @param seriesCount
     */
    public void setSeriesCount(int seriesCount) {
        this.seriesCount = seriesCount;
    }


    /**
     * Gets the xsInfo value for this DataSourceConfiguration.
     * 
     * @return xsInfo
     */
    public com.odb.collector.AxisInfo[] getXsInfo() {
        return xsInfo;
    }


    /**
     * Sets the xsInfo value for this DataSourceConfiguration.
     * 
     * @param xsInfo
     */
    public void setXsInfo(com.odb.collector.AxisInfo[] xsInfo) {
        this.xsInfo = xsInfo;
    }

    public com.odb.collector.AxisInfo getXsInfo(int i) {
        return this.xsInfo[i];
    }

    public void setXsInfo(int i, com.odb.collector.AxisInfo _value) {
        this.xsInfo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSourceConfiguration)) return false;
        DataSourceConfiguration other = (DataSourceConfiguration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dsName==null && other.getDsName()==null) || 
             (this.dsName!=null &&
              this.dsName.equals(other.getDsName()))) &&
            ((this.dsTimeoutInterval==null && other.getDsTimeoutInterval()==null) || 
             (this.dsTimeoutInterval!=null &&
              this.dsTimeoutInterval.equals(other.getDsTimeoutInterval()))) &&
            ((this.publisherID==null && other.getPublisherID()==null) || 
             (this.publisherID!=null &&
              this.publisherID.equals(other.getPublisherID()))) &&
            this.seriesCount == other.getSeriesCount() &&
            ((this.xsInfo==null && other.getXsInfo()==null) || 
             (this.xsInfo!=null &&
              java.util.Arrays.equals(this.xsInfo, other.getXsInfo())));
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
        if (getDsName() != null) {
            _hashCode += getDsName().hashCode();
        }
        if (getDsTimeoutInterval() != null) {
            _hashCode += getDsTimeoutInterval().hashCode();
        }
        if (getPublisherID() != null) {
            _hashCode += getPublisherID().hashCode();
        }
        _hashCode += getSeriesCount();
        if (getXsInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getXsInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getXsInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataSourceConfiguration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://collector.odb.com/", "dataSourceConfiguration"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsTimeoutInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsTimeoutInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("xsInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "xsInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://collector.odb.com/", "axisInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
