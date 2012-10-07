/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.core;

import com.odb.core.dao.dto.GraphInfo;
import com.odb.core.dao.dto.SubscriberViewConfiguration;

/**
 * The Class SubscriberDataSource.
 * 
 * this class is a DTO
 */
public class SubscriberDataSource {
	
	/** The subscriber data source id. */
	private String subscriberID, dataSourceID, graphID, subscriberDataSourceID;

	/** The subscriber view configuration. */
	private SubscriberViewConfiguration subscriberViewConfiguration;
	
	/** The graph info. */
	private GraphInfo graphInfo;
	
	/**
	 * Gets the subscriber id.
	 *
	 * @return the subscriber id
	 */
	public String getSubscriberID() {
		return subscriberID;
	}

	/**
	 * Sets the subscriber id.
	 *
	 * @param subscriberID the new subscriber id
	 */
	public void setSubscriberID(String subscriberID) {
		this.subscriberID = subscriberID;
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
	 * Gets the graph id.
	 *
	 * @return the graph id
	 */
	public String getGraphID() {
		return graphID;
	}

	/**
	 * Sets the graph id.
	 *
	 * @param graphID the new graph id
	 */
	public void setGraphID(String graphID) {
		this.graphID = graphID;
	}

	/**
	 * Gets the subscriber data source id.
	 *
	 * @return the subscriber data source id
	 */
	public String getSubscriberDataSourceID() {
		return subscriberDataSourceID;
	}

	/**
	 * Sets the subscriber data source id.
	 *
	 * @param subscriberDataSourceID the new subscriber data source id
	 */
	public void setSubscriberDataSourceID(String subscriberDataSourceID) {
		this.subscriberDataSourceID = subscriberDataSourceID;
	}

	/**
	 * Gets the subscriber view configuration.
	 *
	 * @return the subscriber view configuration
	 */
	public SubscriberViewConfiguration getSubscriberViewConfiguration() {
		return subscriberViewConfiguration;
	}

	/**
	 * Sets the subscriber view configuration.
	 *
	 * @param subscriberViewConfiguration the new subscriber view configuration
	 */
	public void setSubscriberViewConfiguration(
			SubscriberViewConfiguration subscriberViewConfiguration) {
		this.subscriberViewConfiguration = subscriberViewConfiguration;
	}

	/**
	 * Gets the graph info.
	 *
	 * @return the graph info
	 */
	public GraphInfo getGraphInfo() {
		return graphInfo;
	}

	/**
	 * Sets the graph info.
	 *
	 * @param graphInfo the new graph info
	 */
	public void setGraphInfo(GraphInfo graphInfo) {
		this.graphInfo = graphInfo;
	}
}
