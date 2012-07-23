/**
 * AxisInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public class AxisInfo  implements java.io.Serializable {
    private java.lang.String[] axisLabels;

    private java.lang.String dataSourceAxisName;

    private java.lang.String dataSourceAxisType;

    public AxisInfo() {
    }

    public AxisInfo(
           java.lang.String[] axisLabels,
           java.lang.String dataSourceAxisName,
           java.lang.String dataSourceAxisType) {
           this.axisLabels = axisLabels;
           this.dataSourceAxisName = dataSourceAxisName;
           this.dataSourceAxisType = dataSourceAxisType;
    }


    /**
     * Gets the axisLabels value for this AxisInfo.
     * 
     * @return axisLabels
     */
    public java.lang.String[] getAxisLabels() {
        return axisLabels;
    }


    /**
     * Sets the axisLabels value for this AxisInfo.
     * 
     * @param axisLabels
     */
    public void setAxisLabels(java.lang.String[] axisLabels) {
        this.axisLabels = axisLabels;
    }

    public java.lang.String getAxisLabels(int i) {
        return this.axisLabels[i];
    }

    public void setAxisLabels(int i, java.lang.String _value) {
        this.axisLabels[i] = _value;
    }


    /**
     * Gets the dataSourceAxisName value for this AxisInfo.
     * 
     * @return dataSourceAxisName
     */
    public java.lang.String getDataSourceAxisName() {
        return dataSourceAxisName;
    }


    /**
     * Sets the dataSourceAxisName value for this AxisInfo.
     * 
     * @param dataSourceAxisName
     */
    public void setDataSourceAxisName(java.lang.String dataSourceAxisName) {
        this.dataSourceAxisName = dataSourceAxisName;
    }


    /**
     * Gets the dataSourceAxisType value for this AxisInfo.
     * 
     * @return dataSourceAxisType
     */
    public java.lang.String getDataSourceAxisType() {
        return dataSourceAxisType;
    }


    /**
     * Sets the dataSourceAxisType value for this AxisInfo.
     * 
     * @param dataSourceAxisType
     */
    public void setDataSourceAxisType(java.lang.String dataSourceAxisType) {
        this.dataSourceAxisType = dataSourceAxisType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AxisInfo)) return false;
        AxisInfo other = (AxisInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.axisLabels==null && other.getAxisLabels()==null) || 
             (this.axisLabels!=null &&
              java.util.Arrays.equals(this.axisLabels, other.getAxisLabels()))) &&
            ((this.dataSourceAxisName==null && other.getDataSourceAxisName()==null) || 
             (this.dataSourceAxisName!=null &&
              this.dataSourceAxisName.equals(other.getDataSourceAxisName()))) &&
            ((this.dataSourceAxisType==null && other.getDataSourceAxisType()==null) || 
             (this.dataSourceAxisType!=null &&
              this.dataSourceAxisType.equals(other.getDataSourceAxisType())));
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
        if (getAxisLabels() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAxisLabels());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAxisLabels(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDataSourceAxisName() != null) {
            _hashCode += getDataSourceAxisName().hashCode();
        }
        if (getDataSourceAxisType() != null) {
            _hashCode += getDataSourceAxisType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AxisInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://collector.odb.com/", "axisInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("axisLabels");
        elemField.setXmlName(new javax.xml.namespace.QName("", "axisLabels"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSourceAxisName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataSourceAxisName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSourceAxisType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataSourceAxisType"));
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
