/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.core.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.odb.core.SubscriberDataSource;
import com.odb.core.dao.dto.DataSourceAxisDetailInfo;
import com.odb.core.dao.dto.DataSourceAxisInfo;
import com.odb.core.dao.dto.DataSourceInfo;
import com.odb.core.dao.dto.DataSourceSeries;
import com.odb.core.dao.dto.GraphInfo;
import com.odb.core.dao.dto.PublisherInfo;
import com.odb.core.dao.dto.SubscriberInfo;
import com.odb.core.dao.dto.SubscriberViewConfiguration;
import com.odb.core.dao.dto.ViewConfiguration;


/**
 * The Interface ODBDAO.
 * 
 * This is the OpenDashboard DAO interface.
 * 
 * This class has all the core functions that interact with the database
 */
public interface ODBDAO {

	/**
	 * Adds the publisher.
	 * 
	 * @param pubInfo
	 *            the pub info
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addPublisher(PublisherInfo pubInfo) throws SQLException;

	/**
	 * Adds the data source.
	 * 
	 * @param dsInfo
	 *            the ds info
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addDataSource(DataSourceInfo dsInfo) throws SQLException;

	/**
	 * Adds the data source axis.
	 * 
	 * @param dsAxisInfo
	 *            the ds axis info
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addDataSourceAxis(DataSourceAxisInfo dsAxisInfo) throws SQLException;

	/**
	 * Adds the data source axis detail.
	 * 
	 * @param dsAxisDetailInfo
	 *            the ds axis detail info
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addDataSourceAxisDetail(DataSourceAxisDetailInfo dsAxisDetailInfo) throws SQLException;

	/**
	 * Adds the series data.
	 * 
	 * @param dsSeries
	 *            the ds series
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addSeriesData(DataSourceSeries dsSeries) throws SQLException;

	/**
	 * Adds the subscrbier.
	 * 
	 * @param subInfo
	 *            the sub info
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addSubscrbier(SubscriberInfo subInfo) throws SQLException;

	/**
	 * Adds the subscribe data source.
	 * 
	 * @param subDS
	 *            the sub ds
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addSubscribeDataSource(String subscriberID, String dsID, String graphID, String subDSID) throws SQLException;

	/**
	 * Adds the subscriber view.
	 * 
	 * @param subViewCfg
	 *            the sub view cfg
	 * @throws SQLException
	 *             the sQL exception
	 */
	public void addSubscriberView(SubscriberViewConfiguration subViewCfg) throws SQLException;

	/* Inquiries */
	/**
	 * Gets the publisher by id.
	 * 
	 * @param pubID
	 *            the pub id
	 * @return the publisher by id
	 * @throws SQLException
	 *             the sQL exception
	 */
	public PublisherInfo getPublisherByID(String pubID) throws SQLException;

	/**
	 * Gets the all publishers.
	 * 
	 * @return the all publishers
	 * @throws SQLException
	 *             the sQL exception
	 */
	public ArrayList<PublisherInfo> getAllPublishers() throws SQLException;

	/**
	 * Gets the all data source by publisher id.
	 * 
	 * @param pubID
	 *            the pub id
	 * @return the all data source by publisher id
	 * @throws SQLException
	 *             the sQL exception
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceByPublisherID(String pubID) throws SQLException;


	/**
	 * Gets the all graphs.
	 * 
	 * @return the all graphs
	 */
	public ArrayList<GraphInfo> getAllGraphs() throws SQLException ;

	/**
	 * Gets the series data all by data source id.
	 * 
	 * @param dsID
	 *            the ds id
	 * @return the series data all by data source id
	 */
	public ArrayList<DataSourceSeries> getSeriesDataAllByDataSourceID(String dsID) throws SQLException ;

	/**
	 * Gets the latest series data.
	 * 
	 * @param dsID
	 *            the ds id
	 * @param rowNum
	 *            the row num
	 * @return the latest series data
	 */
	public List<DataSourceSeries> getLatestSeriesData(String dsID, int rowNum) throws SQLException ;

	/**
	 * Subscriber login.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the subscriber info
	 */
	public SubscriberInfo subscriberLogin(String username, String password) throws SQLException ;

	/**
	 * Gets the view configuration list.
	 * 
	 * @return the view configuration list
	 */
	public List<ViewConfiguration> getViewConfigurationList() throws SQLException ;

	/**
	 * Gets the subscriber data source by.
	 * 
	 * @param subscriberId
	 *            the subscriber id
	 * @param viewLocationID
	 *            the view location id
	 * @return SubscriberDataSource with its SubscriberViewConfiguration and
	 *         GraphInfo objects
	 */
	public SubscriberDataSource getSubscriberDataSourceBy(String subscriberId, String viewLocationID) throws SQLException ;

	/**
	 * Gets the subscriber data source by.
	 * 
	 * @param subscriberId
	 *            the subscriber id
	 * @return
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceBySubscriberID(String subscriberId) throws SQLException ;

	/**
	 * Gets the data source by data source id.
	 * 
	 * @param dsID
	 *            the ds id
	 * @return the data source by data source id
	 * @throws SQLException
	 *             the sQL exception
	 */
	public DataSourceInfo getDataSourceByDataSourceID(String dsID) throws SQLException;
	/**
	 * Gets the data source axis info.
	 * 
	 * @param dataSourceID
	 *            the data source id
	 * @return the data source axis info
	 */
	public List<DataSourceAxisInfo> getDataSourceAxisInfo(String dataSourceID) throws SQLException ;

	/**
	 * Gets the data source axis detail info list by.
	 * 
	 * @param axisId
	 *            the axis id
	 * @return the data source axis detail info list by
	 */
	public List<DataSourceAxisDetailInfo> getDataSourceAxisDetailInfoListBy(String axisId) throws SQLException;
}
