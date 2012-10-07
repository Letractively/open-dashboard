/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.core.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.odb.core.dao.dto.DataSourceAxisDetailInfo;

/**
 * The Class DataSourceAxisDetailInfoRowMapper.
 */
public class DataSourceAxisDetailInfoRowMapper implements RowMapper<DataSourceAxisDetailInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public DataSourceAxisDetailInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		DataSourceAxisDetailInfo dxInfoDetail = new DataSourceAxisDetailInfo();
		dxInfoDetail.setDataSourceAxisID(rs.getString("DATASOURCE_AXIS_ID"));
		dxInfoDetail.setAxisLabelIndex(rs.getInt("AXIS_LABEL_INDEX"));
		dxInfoDetail.setAxisLabelValue(rs.getString("AXIS_LABEL_INDEX_VALUE"));
		return dxInfoDetail;
	}

}
