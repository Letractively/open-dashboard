package com.odb.core.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.odb.core.DataSourceAxisDetailInfo;
import com.odb.core.DataSourceAxisInfo;
import com.odb.core.DataSourceInfo;
import com.odb.core.DataSourceSeries;
import com.odb.core.GraphInfo;
import com.odb.core.PublisherInfo;
import com.odb.core.SubscriberDataSource;
import com.odb.core.SubscriberInfo;
import com.odb.core.SubscriberSubscriptions;
import com.odb.core.SubscriberViewConfiguration;
import com.odb.core.ViewConfiguration;

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
	 * @param pubInfo the pub info
	 * @throws SQLException the sQL exception
	 */
	public void addPublisher(PublisherInfo pubInfo) throws SQLException;
	
	/**
	 * Adds the data source.
	 *
	 * @param dsInfo the ds info
	 * @throws SQLException the sQL exception
	 */
	public void addDataSource(DataSourceInfo dsInfo) throws SQLException;
	
	/**
	 * Adds the data source axis.
	 *
	 * @param dsAxisInfo the ds axis info
	 * @throws SQLException the sQL exception
	 */
	public void addDataSourceAxis(DataSourceAxisInfo dsAxisInfo) throws SQLException;
	
	/**
	 * Adds the data source axis detail.
	 *
	 * @param dsAxisDetailInfo the ds axis detail info
	 * @throws SQLException the sQL exception
	 */
	public void addDataSourceAxisDetail(DataSourceAxisDetailInfo dsAxisDetailInfo) throws SQLException;
	
	/**
	 * Adds the series data.
	 *
	 * @param dsSeries the ds series
	 * @throws SQLException the sQL exception
	 */
	public void addSeriesData(DataSourceSeries dsSeries) throws SQLException;
	
	/**
	 * Adds the subscrbier.
	 *
	 * @param subInfo the sub info
	 * @throws SQLException the sQL exception
	 */
	public void addSubscrbier(SubscriberInfo subInfo) throws SQLException;
	
	/**
	 * Adds the subscribe data source.
	 *
	 * @param subDS the sub ds
	 * @throws SQLException the sQL exception
	 */
	public void addSubscribeDataSource(SubscriberDataSource subDS) throws SQLException;
	
	/**
	 * Adds the subscriber view.
	 *
	 * @param subViewCfg the sub view cfg
	 * @throws SQLException the sQL exception
	 */
	public void addSubscriberView(SubscriberViewConfiguration subViewCfg) throws SQLException;

	/*Inquiries*/
	/**
	 * Gets the publisher by id.
	 *
	 * @param pubID the pub id
	 * @return the publisher by id
	 * @throws SQLException the sQL exception
	 */
	public PublisherInfo getPublisherByID(String pubID) throws SQLException;
	
	/**
	 * Gets the all publishers.
	 *
	 * @return the all publishers
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<PublisherInfo> getAllPublishers() throws SQLException;
	
	/**
	 * Gets the all data source by publisher id.
	 *
	 * @param pubID the pub id
	 * @return the all data source by publisher id
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceByPublisherID(String pubID) throws SQLException;
	
	/**
	 * Gets the data source by data source id.
	 *
	 * @param dsID the ds id
	 * @return the data source by data source id
	 * @throws SQLException the sQL exception
	 */
	public DataSourceInfo getDataSourceByDataSourceID(String dsID) throws SQLException;
	
	/**
	 * Gets the all axis info by data source id.
	 *
	 * @param dsID the ds id
	 * @return the all axis info by data source id
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<DataSourceAxisInfo> getAllAxisInfoByDataSourceID(String dsID) throws SQLException;
	
	/**
	 * Gets the all axis details by data source id.
	 *
	 * @param dsAxisID the ds axis id
	 * @return the all axis details by data source id
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<DataSourceAxisDetailInfo> getAllAxisDetailsByDataSourceID(String dsAxisID) throws SQLException;
	
	/**
	 * Gets the all graphs.
	 *
	 * @return the all graphs
	 */
	public ArrayList<GraphInfo> getAllGraphs();
	
	/**
	 * Gets the series data all by data source id.
	 *
	 * @param dsID the ds id
	 * @return the series data all by data source id
	 */
	public ArrayList<DataSourceSeries> getSeriesDataAllByDataSourceID(String dsID);
	
	/**
	 * Gets the latest series data.
	 *
	 * @param dsID the ds id
	 * @param rowNum the row num
	 * @return the latest series data
	 */
	public List<DataSourceSeries> getLatestSeriesData(String dsID, int rowNum);
	
	/**
	 * Subscriber login.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the subscriber info
	 */
	public SubscriberInfo subscriberLogin(String username, String password);
	
	/**
	 * Gets the view configuration list.
	 *
	 * @return the view configuration list
	 */
	public List<ViewConfiguration> getViewConfigurationList();
	
	/**
	 * Gets the subscriber data source by.
	 *
	 * @param subscriberId the subscriber id
	 * @param viewLocationID the view location id
	 * @return SubscriberDataSource with its SubscriberViewConfiguration and GraphInfo objects
	 */
	public SubscriberDataSource getSubscriberDataSourceBy(String subscriberId, String viewLocationID);

	/**
	 * Gets the subscriber data source by.
	 *
	 * @param subscriberId the subscriber id
	 * @return 
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceBySubscriberID(String subscriberId);
	
	/**
	 * Gets the data source axis info.
	 *
	 * @param dataSourceID the data source id
	 * @return the data source axis info
	 */
	public List<DataSourceAxisInfo> getDataSourceAxisInfo(String dataSourceID);
	
	/**
	 * Gets the data source axis detail info list by.
	 *
	 * @param axisId the axis id
	 * @return the data source axis detail info list by
	 */
	public List<DataSourceAxisDetailInfo> getDataSourceAxisDetailInfoListBy(
			String axisId);
}
