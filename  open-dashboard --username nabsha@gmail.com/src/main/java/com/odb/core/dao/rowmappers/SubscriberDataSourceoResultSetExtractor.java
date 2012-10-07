/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
/**
 * 
 */
package com.odb.core.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.odb.core.SubscriberDataSource;
import com.odb.core.dao.dto.GraphInfo;
import com.odb.core.dao.dto.SubscriberViewConfiguration;

/**
 * The Class SubscriberDataSourceoResultSetExtractor.
 *
 */
public class SubscriberDataSourceoResultSetExtractor implements
		ResultSetExtractor<SubscriberDataSource> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	public SubscriberDataSource extractData(ResultSet rs) throws SQLException, DataAccessException {
		SubscriberDataSource sds= null;
		if(rs.next()){
			sds = new SubscriberDataSource();
			sds.setDataSourceID(rs.getString("DATASOURCE_ID"));
			sds.setGraphID(rs.getString("GRAPH_ID"));
			sds.setSubscriberDataSourceID(rs.getString("SUBSCRIBER_DATASOURCE_ID"));
			sds.setSubscriberID(rs.getString("SUBSCRIBER_ID"));
			sds.setSubscriberViewConfiguration(new SubscriberViewConfiguration(rs.getString("SUBSCRIBER_ID"), rs.getString("VIEW_LOCATION_ID"), rs.getString("SUBSCRIBER_DATASOURCE_ID")));
			sds.setGraphInfo(new GraphInfo(rs.getString("GRAPH_ID"), rs.getString("GRAPH_NAME"), rs.getString("GRAPH_TYPE")));
		}
		return sds;
	}

}
