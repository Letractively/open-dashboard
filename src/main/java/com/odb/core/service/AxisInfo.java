package com.odb.core.service;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class AxisInfo.
 */
public class AxisInfo  implements Serializable{
	
	/** The data source axis name. */
	private String dataSourceAxisName;
	
	/** The data source axis type. */
	private String dataSourceAxisType;
	
	/** The axis labels. */
	private ArrayList<String> axisLabels;

	/**
	 * Gets the data source axis name.
	 *
	 * @return the data source axis name
	 */
	public String getDataSourceAxisName() {
		return dataSourceAxisName;
	}
	
	/**
	 * Sets the data source axis name.
	 *
	 * @param dataSourceAxisName the new data source axis name
	 */
	public void setDataSourceAxisName(String dataSourceAxisName) {
		this.dataSourceAxisName = dataSourceAxisName;
	}
	
	/**
	 * Gets the data source axis type.
	 *
	 * @return the data source axis type
	 */
	public String getDataSourceAxisType() {
		return dataSourceAxisType;
	}
	
	/**
	 * Sets the data source axis type.
	 *
	 * @param dataSourceAxisType the new data source axis type
	 */
	public void setDataSourceAxisType(String dataSourceAxisType) {
		this.dataSourceAxisType = dataSourceAxisType;
	}
	
	/**
	 * Gets the axis labels.
	 *
	 * @return the axis labels
	 */
	public ArrayList<String> getAxisLabels() {
		return axisLabels;
	}
	
	/**
	 * Sets the axis labels.
	 *
	 * @param axisLabels the new axis labels
	 */
	public void setAxisLabels(ArrayList<String> axisLabels) {
		this.axisLabels = axisLabels;
	}

}
