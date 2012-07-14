package com.odb.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.odb.core.DataSourceInfo;

/**
 * The Class DataSourceInfoRowMapper.
 */
public class DataSourceInfoRowMapper implements RowMapper<DataSourceInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public DataSourceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		DataSourceInfo dsInfo = new DataSourceInfo();
		dsInfo.setDataSourceID(rs.getString("DATASOURCE_ID"));
		dsInfo.setDataSourceName(rs.getString("DATASOURCE_NAME"));
		dsInfo.setPublisherID(rs.getString("PUBLISHER_ID"));
		dsInfo.setTimeoutInterval(rs.getLong("TIMEOUT_INTERVAL"));
		dsInfo.setSeriesCount(rs.getInt("NUM_OF_SERIES"));
		return dsInfo;
	}

}