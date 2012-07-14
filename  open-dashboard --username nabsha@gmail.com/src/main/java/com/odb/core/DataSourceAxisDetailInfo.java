package com.odb.core;

/**
 * The Class DataSourceAxisDetailInfo.
 * 
 * this class is a DTO for the DataSourceAxisDetailInfo
 */
public class DataSourceAxisDetailInfo {
	
	/** The data source axis id. */
	private String dataSourceAxisID;
	
	/** The axis label index. */
	private int axisLabelIndex;
	
	/** The axis label value. */
	private String axisLabelValue;
	
	/**
	 * Gets the data source axis id.
	 *
	 * @return the data source axis id
	 */
	public String getDataSourceAxisID() {
		return dataSourceAxisID;
	}
	
	/**
	 * Sets the data source axis id.
	 *
	 * @param dataSourceAxisID the new data source axis id
	 */
	public void setDataSourceAxisID(String dataSourceAxisID) {
		this.dataSourceAxisID = dataSourceAxisID;
	}
	
	/**
	 * Gets the axis label index.
	 *
	 * @return the axis label index
	 */
	public int getAxisLabelIndex() {
		return axisLabelIndex;
	}
	
	/**
	 * Sets the axis label index.
	 *
	 * @param axisLabelIndex the new axis label index
	 */
	public void setAxisLabelIndex(int axisLabelIndex) {
		this.axisLabelIndex = axisLabelIndex;
	}
	
	/**
	 * Gets the axis label value.
	 *
	 * @return the axis label value
	 */
	public String getAxisLabelValue() {
		return axisLabelValue;
	}
	
	/**
	 * Sets the axis label value.
	 *
	 * @param axisLabelValue the new axis label value
	 */
	public void setAxisLabelValue(String axisLabelValue) {
		this.axisLabelValue = axisLabelValue;
	}
}
