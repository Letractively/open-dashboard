/**
 * UserDataWrapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.odb.collector;

public class UserDataWrapper  implements java.io.Serializable {
    private com.odb.collector.UserDataWrapperMapEntry[] map;

    public UserDataWrapper() {
    }

    public UserDataWrapper(
           com.odb.collector.UserDataWrapperMapEntry[] map) {
           this.map = map;
    }


    /**
     * Gets the map value for this UserDataWrapper.
     * 
     * @return map
     */
    public com.odb.collector.UserDataWrapperMapEntry[] getMap() {
        return map;
    }


    /**
     * Sets the map value for this UserDataWrapper.
     * 
     * @param map
     */
    public void setMap(com.odb.collector.UserDataWrapperMapEntry[] map) {
        this.map = map;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserDataWrapper)) return false;
        UserDataWrapper other = (UserDataWrapper) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.map==null && other.getMap()==null) || 
             (this.map!=null &&
              java.util.Arrays.equals(this.map, other.getMap())));
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
        if (getMap() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMap());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMap(), i);
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
        new org.apache.axis.description.TypeDesc(UserDataWrapper.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://collector.odb.com/", "userDataWrapper"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("map");
        elemField.setXmlName(new javax.xml.namespace.QName("", "map"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://collector.odb.com/", ">>userDataWrapper>map>entry"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "entry"));
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
