/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.core.dao.dto;

/**
 * The Class DataSourceAxisInfo.
 * 
 * this class is a DTO
 */
public class DataSourceAxisInfo {
	
	/** The data source axis type. */
	private String dataSourceAxisID, dataSourceID, dataSourceAxisName, dataSourceAxisType;
	
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
}
