/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client.dto;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * The Class DataSourceAxisInfo.
 */
@SuppressWarnings("serial")
public class DataSourceAxisInfo implements Serializable{

	/** The data source axis type. */
	private String dataSourceAxisID, dataSourceID, dataSourceAxisName, dataSourceAxisType;

	/** The data source axis detail info list. */
	private ArrayList<DataSourceAxisDetailInfo> dataSourceAxisDetailInfoList;
	
	/**
	 * Instantiates a new data source axis info.
	 */
	public DataSourceAxisInfo(){}


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
	 * Gets the data source axis detail info list.
	 *
	 * @return the data source axis detail info list
	 */
	public ArrayList<DataSourceAxisDetailInfo> getDataSourceAxisDetailInfoList() {
		return dataSourceAxisDetailInfoList;
	}

	/**
	 * Sets the data source axis detail info list.
	 *
	 * @param dataSourceAxisDetailInfoList the new data source axis detail info list
	 */
	public void setDataSourceAxisDetailInfoList(
			ArrayList<DataSourceAxisDetailInfo> dataSourceAxisDetailInfoList) {
		this.dataSourceAxisDetailInfoList = dataSourceAxisDetailInfoList;
	}
}
