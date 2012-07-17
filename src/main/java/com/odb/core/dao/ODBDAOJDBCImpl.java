package com.odb.core.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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
 * The Class ODBDAOJDBCImpl.
 * 
 * This class is an DAO implementation of the DAO interface com.odb.core.dao.ODBDAO
 * 
 *  This class is using Spring JDBC template for database operations
 */
public class ODBDAOJDBCImpl implements ODBDAO {
	
	/** The Spring Named Parameter JDBC template. */
	private NamedParameterJdbcTemplate jdbcTemp;

	/**
	 * Sets the data source.
	 *
	 * @param dataSource the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemp = new NamedParameterJdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addPublisher(com.odb.core.PublisherInfo)
	 */
	public void addPublisher(PublisherInfo pubInfo) {
		String sql = "INSERT INTO ODB_PUBLISHER_INFO(PUBLISHER_ID, PUBLISHER_NAME) VALUES(?,?)";
		
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { pubInfo.getPublisherID(), pubInfo.getPublisherName() });
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addDataSource(com.odb.core.DataSourceInfo)
	 */
	public void addDataSource(DataSourceInfo dsInfo) throws SQLException {
		String sql = "INSERT INTO ODB_DATASOURCE_INFO(DATASOURCE_ID, PUBLISHER_ID, DATASOURCE_NAME, TIMEOUT_INTERVAL) VALUES(?,?,?,?)";
		
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { dsInfo.getDataSourceID(), dsInfo.getPublisherID(), dsInfo.getDataSourceName(), dsInfo.getTimeoutInterval() });

	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addDataSourceAxis(com.odb.core.DataSourceAxisInfo)
	 */
	public void addDataSourceAxis(DataSourceAxisInfo dsAxisInfo) throws SQLException {
		String sql = "INSERT INTO ODB_DATASOURCE_AXIS_CONFIG(DATASOURCE_AXIS_ID, DATASOURCE_ID, DATASOURCE_AXIS_NAME, DATASOURCE_AXIS_TYPE) VALUES(?,?,?,?)";
		
		jdbcTemp.getJdbcOperations().update(
				sql,
				new Object[] { dsAxisInfo.getDataSourceAxisID(), dsAxisInfo.getDataSourceID(), dsAxisInfo.getDataSourceAxisName(),
						dsAxisInfo.getDataSourceAxisType() });
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addDataSourceAxisDetail(com.odb.core.DataSourceAxisDetailInfo)
	 */
	public void addDataSourceAxisDetail(DataSourceAxisDetailInfo dsAxisDetailInfo) throws SQLException {
		String sql = "INSERT INTO ODB_DATASOURCE_AXIS_DTL_CONFIG(DATASOURCE_AXIS_ID, AXIS_LABEL_INDEX, AXIS_LABEL_INDEX_VALUE) VALUES(?,?,?)";
		
		jdbcTemp.getJdbcOperations().update(sql,
				new Object[] { dsAxisDetailInfo.getDataSourceAxisID(), dsAxisDetailInfo.getAxisLabelIndex(), dsAxisDetailInfo.getAxisLabelValue() });
	}
	
	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addSeriesData(com.odb.core.DataSourceSeries)
	 */
	public void addSeriesData(DataSourceSeries dsSeries) throws SQLException {
		String sql = "INSERT INTO ODB_DATASOURCE_DATASERIES(DATASOURCE_ID, DATASOURCE_SERIES_INDEX, DATASOURCE_SERIES_IDX_SEQ_VAL) VALUES(?,?,?)";
		
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { dsSeries.getDataSourceID(), dsSeries.getSeriesIndex(), dsSeries.getSeriesIndexSeqVal() });
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addSubscrbier(com.odb.core.SubscriberInfo)
	 */
	public void addSubscrbier(SubscriberInfo subInfo) throws SQLException {
		String sql = "INSERT INTO ODB_SUBSCRIBER_INFO(SUBSCRIBER_ID, SUBSCRIBER_NAME, SUBSCRIBER_PASSWORD) VALUES(?,?,?)";
		
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { subInfo.getSubscriberID(), subInfo.getSubscriberName(), subInfo.getSubscriberPassword() });

	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addSubscribeDataSource(com.odb.core.SubscriberDataSource)
	 */
	public void addSubscribeDataSource(SubscriberDataSource subDS) throws SQLException {
		String sql = "INSERT INTO ODB_SUB_DATASOURCES(SUBSCRIBER_ID, DATASOURCE_ID, GRAPH_ID, SUBSCRIBER_DATASOURCE_ID) VALUES(?,?,?,?)";
		
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { subDS.getSubscriberID(), subDS.getDataSourceID(), subDS.getGraphID(), subDS.getSubscriberDataSourceID() });
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#addSubscriberView(com.odb.core.SubscriberViewConfiguration)
	 */
	public void addSubscriberView(SubscriberViewConfiguration subViewCfg) throws SQLException {
		String sql = "INSERT INTO ODB_SUB_VIEW_CONFIG(SUBSCRIBER_ID, VIEW_LOCATION_ID, SUBSCRIBER_DATASOURCE_ID) VALUES(?,?,?)";
		
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { subViewCfg.getSubscriberID(), subViewCfg.getViewLocationID(), subViewCfg.getSubsriberDataSourceID() });
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getPublisherByID(java.lang.String)
	 */
	public PublisherInfo getPublisherByID(String pubID) {
		String sql = "SELECT * FROM ODB_PUBLISHER_INFO WHERE PUBLISHER_ID=?";
		
		PublisherInfo pInfo = (PublisherInfo) jdbcTemp.getJdbcOperations().queryForObject(sql, new Object[] { pubID }, new PublisherInfoRowMapper());
		return pInfo;
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getAllPublishers()
	 */
	public ArrayList<PublisherInfo> getAllPublishers() throws SQLException {
		String sql = "SELECT * FROM ODB_PUBLISHER_INFO";
		
		return (ArrayList<PublisherInfo>) jdbcTemp.getJdbcOperations().query(sql, new PublisherInfoRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getSubscriberDataSourceBy(java.lang.String, java.lang.String)
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceBySubscriberID(String subscriberId) {
		String sql = "SELECT ODI.* FROM ODB_SUB_DATASOURCES SDS, ODB_DATASOURCE_INFO ODI WHERE SDS.SUBSCRIBER_ID=? AND SDS.DATASOURCE_ID=ODI.DATASOURCE_ID";
		return (ArrayList<DataSourceInfo>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { subscriberId }, new DataSourceInfoRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getAllDataSourceByPublisherID(java.lang.String)
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceByPublisherID(String pubID) throws SQLException {
		String sql = "SELECT * FROM ODB_DATASOURCE_INFO WHERE PUBLISHER_ID=?";
		
		return (ArrayList<DataSourceInfo>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { pubID }, new DataSourceInfoRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getDataSourceByDataSourceID(java.lang.String)
	 */
	public DataSourceInfo getDataSourceByDataSourceID(String dsID) throws SQLException {
		String sql = "SELECT * FROM ODB_DATASOURCE_INFO WHERE DATASOURCE_ID=:dataSourceID";
		SqlParameterSource param = new MapSqlParameterSource("dataSourceID", dsID);
		return jdbcTemp.query(sql, param, new DataSourceInfoResultSetExtractor());
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getAllAxisInfoByDataSourceID(java.lang.String)
	 */
	public ArrayList<DataSourceAxisInfo> getAllAxisInfoByDataSourceID(String dsID) throws SQLException {
		String sql = "SELECT * FROM ODB_DATASOURCE_AXIS_CONFIG WHERE DATASOURCE_ID=?";
		
		return (ArrayList<DataSourceAxisInfo>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { dsID }, new DataSourceAxisInfoRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getAllAxisDetailsByDataSourceID(java.lang.String)
	 */
	public ArrayList<DataSourceAxisDetailInfo> getAllAxisDetailsByDataSourceID(String dsAxisID) throws SQLException {
		String sql = "SELECT * FROM ODB_DATASOURCE_AXIS_DTL_CONFIG WHERE DATASOURCE_AXIS_ID=?";
		
		return (ArrayList<DataSourceAxisDetailInfo>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { dsAxisID }, new DataSourceAxisDetailInfoRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getSeriesDataAllByDataSourceID(java.lang.String)
	 */
	public ArrayList<DataSourceSeries> getSeriesDataAllByDataSourceID(String dsID) {
		String sql = "SELECT * FROM ODB_DATASOURCE_DATASERIES WHERE DATASOURCE_ID=?";
		
		return (ArrayList<DataSourceSeries>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { dsID }, new DataSourceSeriesRowMapper());
	}
	
	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getLatestSeriesData(java.lang.String, int)
	 */
	public List<DataSourceSeries> getLatestSeriesData(String dsID, int rowNum) {
		//String sql = "SELECT * FROM ODB_DATASOURCE_DATASERIES WHERE DATASOURCE_ID = ? ORDER BY DATASOURCE_SERIES_INDEX_SEQ DESC AND ROWNUM < "+rowNum;
		String sql = "SELECT * FROM ODB_DATASOURCE_DATASERIES WHERE DATASOURCE_ID = ? ORDER BY DATASOURCE_SERIES_INDEX_SEQ DESC LIMIT 0,"+rowNum;
		return jdbcTemp.getJdbcOperations().query(sql, new Object[] { dsID }, new DataSourceSeriesRowMapper());
	}
	
	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getAllGraphs()
	 */
	public ArrayList<GraphInfo> getAllGraphs() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#subscriberLogin(java.lang.String, java.lang.String)
	 */
	public SubscriberInfo subscriberLogin(String username, String password) {
		String sql = "SELECT * FROM ODB_SUBSCRIBER_INFO WHERE SUBSCRIBER_NAME=:subscriberName AND SUBSCRIBER_PASSWORD=:subscriberPassword";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("subscriberName", username);
		paramMap.put("subscriberPassword", password);
		return jdbcTemp.query(sql, paramMap, new SubscriberInfoResultSetExtractor() );
	}
	
	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getSubscriberDataSourceBy(java.lang.String, java.lang.String)
	 */
	public SubscriberDataSource getSubscriberDataSourceBy(String subscriberId, String viewLocationID) {
		String sql = "SELECT SDS.* , SVC.VIEW_LOCATION_ID, GC.GRAPH_NAME, GC.GRAPH_TYPE FROM ODB_SUB_DATASOURCES SDS, ODB_SUB_VIEW_CONFIG SVC, ODB_GRAPH_CONFIG GC WHERE SDS.SUBSCRIBER_DATASOURCE_ID=SVC.SUBSCRIBER_DATASOURCE_ID AND SDS.GRAPH_ID=GC.GRAPH_ID AND SDS.SUBSCRIBER_ID=:subscriberID AND SVC.VIEW_LOCATION_ID=:viewLocationID";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("subscriberID", subscriberId);
		paramMap.put("viewLocationID", viewLocationID);
		return jdbcTemp.query(sql, paramMap, new SubscriberDataSourceoResultSetExtractor() );
	}



	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getViewConfigurationList()
	 */
	public List<ViewConfiguration> getViewConfigurationList() {
		String sql = "SELECT * FROM ODB_VIEW_CONFIG WHERE IS_DESPLAYED=1";
		return jdbcTemp.getJdbcOperations().query(sql, new ViewConfigurationRowMapper() );
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getDataSourceAxisInfo(java.lang.String)
	 */
	public List<DataSourceAxisInfo> getDataSourceAxisInfo(String dataSourceID) {
		String sql = "select * from ODB_DATASOURCE_AXIS_CONFIG where DATASOURCE_ID=:dataSourceID";
		SqlParameterSource param = new MapSqlParameterSource("dataSourceID", dataSourceID);
		return jdbcTemp.query(sql, param, new DataSourceAxisInfoRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.odb.core.dao.ODBDAO#getDataSourceAxisDetailInfoListBy(java.lang.String)
	 */
	public List<DataSourceAxisDetailInfo> getDataSourceAxisDetailInfoListBy(String axisId) {
		String sql = "SELECT * FROM ODB_DATASOURCE_AXIS_DTL_CONFIG WHERE DATASOURCE_AXIS_ID=:dataSourceAxisID";
		SqlParameterSource param = new MapSqlParameterSource("dataSourceAxisID", axisId);
		return jdbcTemp.query(sql, param, new DataSourceAxisDetailInfoRowMapper());
	}

}
