package com.odb.core.service;

import java.util.ArrayList;

/**
 * The Class DataSourceConfiguration.
 */
public class DataSourceConfiguration {
	
	/** The ds name. */
	private String dsName;

	/** The xs info. */
	private ArrayList<AxisInfo> xsInfo;
	
	/** The ds timeout interval. */
	private Long dsTimeoutInterval;

	/**
	 * Gets the xs info.
	 *
	 * @return the xs info
	 */
	public ArrayList<AxisInfo> getXsInfo() {
		return xsInfo;
	}

	/**
	 * Sets the xs info.
	 *
	 * @param xsInfo the new xs info
	 */
	public void setXsInfo(ArrayList<AxisInfo> xsInfo) {
		this.xsInfo = xsInfo;
	}

	/**
	 * Gets the ds name.
	 *
	 * @return the ds name
	 */
	public String getDsName() {
		return dsName;
	}

	/**
	 * Sets the ds name.
	 *
	 * @param dsName the new ds name
	 */
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	/**
	 * Gets the ds timeout interval.
	 *
	 * @return the ds timeout interval
	 */
	public Long getDsTimeoutInterval() {
		return dsTimeoutInterval;
	}

	/**
	 * Sets the ds timeout interval.
	 *
	 * @param dsTimeoutInterval the new ds timeout interval
	 */
	public void setDsTimeoutInterval(Long dsTimeoutInterval) {
		this.dsTimeoutInterval = dsTimeoutInterval;
	}

	
}