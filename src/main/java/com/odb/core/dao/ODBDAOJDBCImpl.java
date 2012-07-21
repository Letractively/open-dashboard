package com.odb.core.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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
import com.odb.core.dao.rowmappers.DataSourceAxisDetailInfoRowMapper;
import com.odb.core.dao.rowmappers.DataSourceAxisInfoRowMapper;
import com.odb.core.dao.rowmappers.DataSourceInfoResultSetExtractor;
import com.odb.core.dao.rowmappers.DataSourceInfoRowMapper;
import com.odb.core.dao.rowmappers.DataSourceSeriesRowMapper;
import com.odb.core.dao.rowmappers.PublisherInfoRowMapper;
import com.odb.core.dao.rowmappers.SubscriberDataSourceoResultSetExtractor;
import com.odb.core.dao.rowmappers.SubscriberInfoResultSetExtractor;
import com.odb.core.dao.rowmappers.ViewConfigurationRowMapper;
import com.odb.core.service.OpenDashBoard;
import com.thoughtworks.xstream.XStream;

/**
 * The Class ODBDAOJDBCImpl.
 * 
 * This class is an DAO implementation of the DAO interface
 * com.odb.core.dao.ODBDAO
 * 
 * This class is using Spring JDBC template for database operations
 */
public class ODBDAOJDBCImpl implements ODBDAO {

	/** The log. */
	private static Logger log = Logger.getLogger(ODBDAOJDBCImpl.class);

	/** The Spring Named Parameter JDBC template. */
	private NamedParameterJdbcTemplate jdbcTemp;

	/**
	 * Sets the data source.
	 * 
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemp = new NamedParameterJdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#addPublisher(com.odb.core.PublisherInfo)
	 */
	public void addPublisher(PublisherInfo pubInfo) {
		String sql = "INSERT INTO ODB_PUBLISHER_INFO(PUBLISHER_ID, PUBLISHER_NAME) VALUES(?,?)";
		log.debug("PublisherInfo" + pubInfo);
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { pubInfo.getPublisherID(), pubInfo.getPublisherName() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#addDataSource(com.odb.core.DataSourceInfo)
	 */
	public void addDataSource(DataSourceInfo dsInfo) throws SQLException {
		String sql = "INSERT INTO ODB_DATASOURCE_INFO(DATASOURCE_ID, PUBLISHER_ID, DATASOURCE_NAME, TIMEOUT_INTERVAL) VALUES(?,?,?,?)";

		log.debug("DataSourceInfo" + dsInfo);
		jdbcTemp.getJdbcOperations().update(sql,
				new Object[] { dsInfo.getDataSourceID(), dsInfo.getPublisherID(), dsInfo.getDataSourceName(), dsInfo.getTimeoutInterval() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.odb.core.dao.ODBDAO#addDataSourceAxis(com.odb.core.DataSourceAxisInfo
	 * )
	 */
	public void addDataSourceAxis(DataSourceAxisInfo dsAxisInfo) throws SQLException {
		log.debug("DataSourceAxisInfo# " + new XStream().toXML(dsAxisInfo));
		String sql = "INSERT INTO ODB_DATASOURCE_AXIS_CONFIG(DATASOURCE_AXIS_ID, DATASOURCE_ID, DATASOURCE_AXIS_NAME, DATASOURCE_AXIS_TYPE) VALUES(?,?,?,?)";

		jdbcTemp.getJdbcOperations().update(
				sql,
				new Object[] { dsAxisInfo.getDataSourceAxisID(), dsAxisInfo.getDataSourceID(), dsAxisInfo.getDataSourceAxisName(),
						dsAxisInfo.getDataSourceAxisType() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#addDataSourceAxisDetail(com.odb.core.
	 * DataSourceAxisDetailInfo)
	 */
	public void addDataSourceAxisDetail(DataSourceAxisDetailInfo dsAxisDetailInfo) throws SQLException {
		String sql = "INSERT INTO ODB_DATASOURCE_AXIS_DTL_CONFIG(DATASOURCE_AXIS_ID, AXIS_LABEL_INDEX, AXIS_LABEL_INDEX_VALUE) VALUES(?,?,?)";

		log.debug("dsAxisDetailInfo" + dsAxisDetailInfo);
		jdbcTemp.getJdbcOperations().update(sql,
				new Object[] { dsAxisDetailInfo.getDataSourceAxisID(), dsAxisDetailInfo.getAxisLabelIndex(), dsAxisDetailInfo.getAxisLabelValue() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#addSeriesData(com.odb.core.DataSourceSeries)
	 */
	public void addSeriesData(DataSourceSeries dsSeries) throws SQLException {
		String sql = "INSERT INTO ODB_DATASOURCE_DATASERIES(DATASOURCE_ID, DATASOURCE_SERIES_INDEX, DATASOURCE_SERIES_IDX_SEQ_VAL) VALUES(?,?,?)";

		jdbcTemp.getJdbcOperations().update(sql, new Object[] { dsSeries.getDataSourceID(), dsSeries.getSeriesIndex(), dsSeries.getSeriesIndexSeqVal() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#addSubscrbier(com.odb.core.SubscriberInfo)
	 */
	public void addSubscrbier(SubscriberInfo subInfo) throws SQLException {
		String sql = "INSERT INTO ODB_SUBSCRIBER_INFO(SUBSCRIBER_ID, SUBSCRIBER_NAME, SUBSCRIBER_PASSWORD) VALUES(?,?,?)";

		log.debug("SubscriberInfo" + subInfo);
		jdbcTemp.getJdbcOperations().update(sql, new Object[] { subInfo.getSubscriberID(), subInfo.getSubscriberName(), subInfo.getSubscriberPassword() });

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#addSubscribeDataSource(com.odb.core.
	 * SubscriberDataSource)
	 */
	public void addSubscribeDataSource(SubscriberDataSource subDS) throws SQLException {
		String sql = "INSERT INTO ODB_SUB_DATASOURCES(SUBSCRIBER_ID, DATASOURCE_ID, GRAPH_ID, SUBSCRIBER_DATASOURCE_ID) VALUES(?,?,?,?)";

		log.debug("SubscriberDataSource" + subDS);
		jdbcTemp.getJdbcOperations().update(sql,
				new Object[] { subDS.getSubscriberID(), subDS.getDataSourceID(), subDS.getGraphID(), subDS.getSubscriberDataSourceID() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#addSubscriberView(com.odb.core.
	 * SubscriberViewConfiguration)
	 */
	public void addSubscriberView(SubscriberViewConfiguration subViewCfg) throws SQLException {
		String sql = "INSERT INTO ODB_SUB_VIEW_CONFIG(SUBSCRIBER_ID, VIEW_LOCATION_ID, SUBSCRIBER_DATASOURCE_ID) VALUES(?,?,?)";

		log.debug("SubscriberViewConfiguration" + subViewCfg);
		jdbcTemp.getJdbcOperations().update(sql,
				new Object[] { subViewCfg.getSubscriberID(), subViewCfg.getViewLocationID(), subViewCfg.getSubsriberDataSourceID() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getPublisherByID(java.lang.String)
	 */
	public PublisherInfo getPublisherByID(String pubID) throws SQLException {
		String sql = "SELECT * FROM ODB_PUBLISHER_INFO WHERE PUBLISHER_ID=?";

		PublisherInfo pInfo = (PublisherInfo) jdbcTemp.getJdbcOperations().queryForObject(sql, new Object[] { pubID }, new PublisherInfoRowMapper());
		log.debug("PublisherInfo" + pInfo);
		return pInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getAllPublishers()
	 */
	public ArrayList<PublisherInfo> getAllPublishers() throws SQLException {
		String sql = "SELECT * FROM ODB_PUBLISHER_INFO";

		ArrayList<PublisherInfo> pubList = (ArrayList<PublisherInfo>) jdbcTemp.getJdbcOperations().query(sql, new PublisherInfoRowMapper());
		log.debug("ArrayList<PublisherInfo>" + pubList);
		return pubList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getSubscriberDataSourceBy(java.lang.String,
	 * java.lang.String)
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceBySubscriberID(String subscriberId) {
		String sql = "SELECT ODI.* FROM ODB_SUB_DATASOURCES SDS, ODB_DATASOURCE_INFO ODI WHERE SDS.SUBSCRIBER_ID=? AND SDS.DATASOURCE_ID=ODI.DATASOURCE_ID";
		ArrayList<DataSourceInfo> subDSList = (ArrayList<DataSourceInfo>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { subscriberId },
				new DataSourceInfoRowMapper());
		log.debug("ArrayList<DataSourceInfo>" + subDSList);
		return subDSList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.odb.core.dao.ODBDAO#getAllDataSourceByPublisherID(java.lang.String)
	 */
	public ArrayList<DataSourceInfo> getAllDataSourceByPublisherID(String pubID) throws SQLException {
		String sql = "SELECT * FROM ODB_DATASOURCE_INFO WHERE PUBLISHER_ID=?";
		ArrayList<DataSourceInfo> pubDSList = (ArrayList<DataSourceInfo>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { pubID }, new DataSourceInfoRowMapper());
		log.debug("ArrayList<DataSourceInfo>" + pubDSList);
		return pubDSList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.odb.core.dao.ODBDAO#getSeriesDataAllByDataSourceID(java.lang.String)
	 */
	public ArrayList<DataSourceSeries> getSeriesDataAllByDataSourceID(String dsID) {
		String sql = "SELECT * FROM ODB_DATASOURCE_DATASERIES WHERE DATASOURCE_ID=?";
		ArrayList<DataSourceSeries> dsSeriesList = (ArrayList<DataSourceSeries>) jdbcTemp.getJdbcOperations().query(sql, new Object[] { dsID }, new DataSourceSeriesRowMapper());
		log.debug("ArrayList<DataSourceSeries>" + dsSeriesList);
		return dsSeriesList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getLatestSeriesData(java.lang.String, int)
	 */
	public List<DataSourceSeries> getLatestSeriesData(String dsID, int rowNum) throws SQLException {
		// String sql =
		// "SELECT * FROM ODB_DATASOURCE_DATASERIES WHERE DATASOURCE_ID = ? ORDER BY DATASOURCE_SERIES_INDEX_SEQ DESC AND ROWNUM < "+rowNum;
		String sql = "SELECT * FROM ODB_DATASOURCE_DATASERIES WHERE DATASOURCE_ID = ? ORDER BY DATASOURCE_SERIES_INDEX_SEQ DESC LIMIT 0," + rowNum;
		List<DataSourceSeries> latestData = jdbcTemp.getJdbcOperations().query(sql, new Object[] { dsID }, new DataSourceSeriesRowMapper());
		log.debug("ArrayList<DataSourceSeries>" + latestData);
		return latestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getAllGraphs()
	 */
	public ArrayList<GraphInfo> getAllGraphs() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#subscriberLogin(java.lang.String,
	 * java.lang.String)
	 */
	public SubscriberInfo subscriberLogin(String username, String password) throws SQLException {
		String sql = "SELECT * FROM ODB_SUBSCRIBER_INFO WHERE SUBSCRIBER_NAME=:subscriberName AND SUBSCRIBER_PASSWORD=:subscriberPassword";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("subscriberName", username);
		paramMap.put("subscriberPassword", password);
		return jdbcTemp.query(sql, paramMap, new SubscriberInfoResultSetExtractor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getSubscriberDataSourceBy(java.lang.String,
	 * java.lang.String)
	 */
	public SubscriberDataSource getSubscriberDataSourceBy(String subscriberId, String viewLocationID) throws SQLException {
		String sql = "SELECT SDS.* , SVC.VIEW_LOCATION_ID, GC.GRAPH_NAME, GC.GRAPH_TYPE FROM ODB_SUB_DATASOURCES SDS, ODB_SUB_VIEW_CONFIG SVC, ODB_GRAPH_CONFIG GC WHERE SDS.SUBSCRIBER_DATASOURCE_ID=SVC.SUBSCRIBER_DATASOURCE_ID AND SDS.GRAPH_ID=GC.GRAPH_ID AND SDS.SUBSCRIBER_ID=:subscriberID AND SVC.VIEW_LOCATION_ID=:viewLocationID";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("subscriberID", subscriberId);
		paramMap.put("viewLocationID", viewLocationID);
		SubscriberDataSource sds = jdbcTemp.query(sql, paramMap, new SubscriberDataSourceoResultSetExtractor());
		log.debug("SubscriberDataSource" + sds);
		return sds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getViewConfigurationList()
	 */
	public List<ViewConfiguration> getViewConfigurationList() throws SQLException {
		String sql = "SELECT * FROM ODB_VIEW_CONFIG WHERE IS_DESPLAYED=1";
		List<ViewConfiguration> vcfgList = jdbcTemp.getJdbcOperations().query(sql, new ViewConfigurationRowMapper());
		return vcfgList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.odb.core.dao.ODBDAO#getDataSourceByDataSourceID(java.lang.String)
	 */
	public DataSourceInfo getDataSourceByDataSourceID(String dsID) throws SQLException {
		String sql = "SELECT * FROM ODB_DATASOURCE_INFO WHERE DATASOURCE_ID=:dataSourceID";
		SqlParameterSource param = new MapSqlParameterSource("dataSourceID", dsID);
		return jdbcTemp.query(sql, param, new DataSourceInfoResultSetExtractor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odb.core.dao.ODBDAO#getDataSourceAxisInfo(java.lang.String)
	 */
	public List<DataSourceAxisInfo> getDataSourceAxisInfo(String dataSourceID) throws SQLException {
		String sql = "select * from ODB_DATASOURCE_AXIS_CONFIG where DATASOURCE_ID=:dataSourceID";
		SqlParameterSource param = new MapSqlParameterSource("dataSourceID", dataSourceID);
		return jdbcTemp.query(sql, param, new DataSourceAxisInfoRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.odb.core.dao.ODBDAO#getDataSourceAxisDetailInfoListBy(java.lang.String
	 * )
	 */
	public List<DataSourceAxisDetailInfo> getDataSourceAxisDetailInfoListBy(String axisId) throws SQLException {
		String sql = "SELECT * FROM ODB_DATASOURCE_AXIS_DTL_CONFIG WHERE DATASOURCE_AXIS_ID=:dataSourceAxisID";
		SqlParameterSource param = new MapSqlParameterSource("dataSourceAxisID", axisId);
		return jdbcTemp.query(sql, param, new DataSourceAxisDetailInfoRowMapper());
	}

}
