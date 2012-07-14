package com.odb.view.dashboard.client.dto;

import java.io.Serializable;


/**
 * The Class DataSourceInfo.
 */
@SuppressWarnings("serial")
public class DataSourceInfo implements Serializable {

	/** The data source name. */
	private String dataSourceID, publisherID, dataSourceName;
	
	/** The timeout interval. */
	private Long timeoutInterval;
	
	private int seriesCount;
	/**
	 * Instantiates a new data source info.
	 */
	public DataSourceInfo(){}

	/**
	 * Gets the timeout interval.
	 *
	 * @return the timeout interval
	 */
	public Long getTimeoutInterval() {
		return timeoutInterval;
	}

	/**
	 * Sets the timeout interval.
	 *
	 * @param timeoutInterval the new timeout interval
	 */
	public void setTimeoutInterval(Long timeoutInterval) {
		this.timeoutInterval = timeoutInterval;
	}

	/**
	 * Gets the data source id.
	 *
	 * @return the data source id
	 */
	public String getDataSourceID() {
		return dataSourceID;
	}

	/**
	 * Sets the data source id.
	 *
	 * @param dataSourceID the new data source id
	 */
	public void setDataSourceID(String dataSourceID) {
		this.dataSourceID = dataSourceID;
	}

	/**
	 * Gets the publisher id.
	 *
	 * @return the publisher id
	 */
	public String getPublisherID() {
		return publisherID;
	}

	/**
	 * Sets the publisher id.
	 *
	 * @param publisherID the new publisher id
	 */
	public void setPublisherID(String publisherID) {
		this.publisherID = publisherID;
	}

	/**
	 * Gets the data source name.
	 *
	 * @return the data source name
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * Sets the data source name.
	 *
	 * @param dataSourceName the new data source name
	 */
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public int getSeriesCount() {
		return seriesCount;
	}

	public void setSeriesCount(int seriesCount) {
		this.seriesCount = seriesCount;
	}
}
