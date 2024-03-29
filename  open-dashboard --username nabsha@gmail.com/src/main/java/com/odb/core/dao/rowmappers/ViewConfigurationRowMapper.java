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

import com.odb.core.dao.dto.ViewConfiguration;

/**
 * The Class ViewConfigurationRowMapper.
 */
public class ViewConfigurationRowMapper implements RowMapper<ViewConfiguration>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public ViewConfiguration mapRow(ResultSet rs, int rowNum) throws SQLException {
		ViewConfiguration vc = new ViewConfiguration();
		vc.setStartX(rs.getInt("VIEW_DRAW_X"));
		vc.setStartY(rs.getInt("VIEW_DRAW_Y"));
		vc.setViewHeight(rs.getInt("VIEW_HIEGHT"));
		vc.setViewWidth(rs.getInt("VIEW_WIDTH"));
		vc.setViewLocationID(rs.getString("VIEW_LOCATION_ID"));
		return vc;
	}

}
