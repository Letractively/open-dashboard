/**
 * PublisherInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public class PublisherInfo  implements java.io.Serializable {
    private java.lang.String publisherID;

    private java.lang.String publisherName;

    public PublisherInfo() {
    }

    public PublisherInfo(
           java.lang.String publisherID,
           java.lang.String publisherName) {
           this.publisherID = publisherID;
           this.publisherName = publisherName;
    }


    /**
     * Gets the publisherID value for this PublisherInfo.
     * 
     * @return publisherID
     */
    public java.lang.String getPublisherID() {
        return publisherID;
    }


    /**
     * Sets the publisherID value for this PublisherInfo.
     * 
     * @param publisherID
     */
    public void setPublisherID(java.lang.String publisherID) {
        this.publisherID = publisherID;
    }


    /**
     * Gets the publisherName value for this PublisherInfo.
     * 
     * @return publisherName
     */
    public java.lang.String getPublisherName() {
        return publisherName;
    }


    /**
     * Sets the publisherName value for this PublisherInfo.
     * 
     * @param publisherName
     */
    public void setPublisherName(java.lang.String publisherName) {
        this.publisherName = publisherName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PublisherInfo)) return false;
        PublisherInfo other = (PublisherInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.publisherID==null && other.getPublisherID()==null) || 
             (this.publisherID!=null &&
              this.publisherID.equals(other.getPublisherID()))) &&
            ((this.publisherName==null && other.getPublisherName()==null) || 
             (this.publisherName!=null &&
              this.publisherName.equals(other.getPublisherName())));
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
        if (getPublisherID() != null) {
            _hashCode += getPublisherID().hashCode();
        }
        if (getPublisherName() != null) {
            _hashCode += getPublisherName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PublisherInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://collector.odb.com/", "publisherInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisherID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publisherID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisherName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publisherName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
