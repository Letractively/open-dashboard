package com.odb.view.dashboard.client.dto;

import java.io.Serializable;


/**
 * The Class DataSourceAxisDetailInfo.
 */
@SuppressWarnings("serial")
public class DataSourceAxisDetailInfo implements Serializable{

	/** The data source axis id. */
	private String dataSourceAxisID;
	
	/** The axis label index. */
	private int axisLabelIndex;
	
	/** The axis label value. */
	private String axisLabelValue;
	
	
	/**
	 * Instantiates a new data source axis detail info.
	 */
	public DataSourceAxisDetailInfo() {
	}
	
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
