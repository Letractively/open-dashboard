/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.core.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.odb.core.dao.dto.PublisherInfo;


public class PublisherInfoResultSetExtractor implements ResultSetExtractor<PublisherInfo> {

	public PublisherInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
		PublisherInfo pInfo = new PublisherInfo();
		pInfo.setPublisherID(rs.getString("PUBLISHER_ID"));
		pInfo.setPublisherName(rs.getString("PUBLISHER_NAME"));
		return pInfo;
	}

}
