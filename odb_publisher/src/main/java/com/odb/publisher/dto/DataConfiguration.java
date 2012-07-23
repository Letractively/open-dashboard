package com.odb.publisher.dto;

import java.util.ArrayList;

public class DataConfiguration {
	private String dataSourceName;
	private int timeoutInterval;
	private int numberOfSeries;
	private ArrayList<AxisDetails> axisDetails;

	public DataConfiguration() {
		axisDetails = new ArrayList<AxisDetails>();
	}
	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public int getTimeoutInterval() {
		return timeoutInterval;
	}

	public void setTimeoutInterval(int timeoutInterval) {
		this.timeoutInterval = timeoutInterval;
	}

	public int getNumberOfSeries() {
		return numberOfSeries;
	}

	public void setNumberOfSeries(int numberOfSeries) {
		this.numberOfSeries = numberOfSeries;
	}

	public ArrayList<AxisDetails> getAxisDetails() {
		return axisDetails;
	}

	public void setAxisDetails(ArrayList<AxisDetails> axisDetails) {
		this.axisDetails = axisDetails;
	}

}
