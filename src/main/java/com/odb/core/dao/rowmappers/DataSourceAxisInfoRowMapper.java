package com.odb.core.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.odb.core.dao.dto.DataSourceAxisInfo;

/**
 * The Class DataSourceAxisInfoRowMapper.
 */
public class DataSourceAxisInfoRowMapper implements RowMapper<DataSourceAxisInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public DataSourceAxisInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		DataSourceAxisInfo dxInfo = new DataSourceAxisInfo();
		dxInfo.setDataSourceID(rs.getString("DATASOURCE_ID"));
		dxInfo.setDataSourceAxisID(rs.getString("DATASOURCE_AXIS_ID"));
		dxInfo.setDataSourceAxisName(rs.getString("DATASOURCE_AXIS_NAME"));
		dxInfo.setDataSourceAxisType(rs.getString("DATASOURCE_AXIS_TYPE"));
		return dxInfo;
	}
	
}
