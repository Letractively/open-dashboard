/**
 * 
 */
package com.odb.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.odb.core.GraphInfo;
import com.odb.core.SubscriberDataSource;
import com.odb.core.SubscriberSubscriptions;
import com.odb.core.SubscriberViewConfiguration;

/**
 * The Class SubscriberDataSourceoResultSetExtractor.
 *
 */
public class SubscriberSubscriptionsResultSetExtractor implements
		ResultSetExtractor<SubscriberSubscriptions> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	public SubscriberSubscriptions extractData(ResultSet rs) throws SQLException, DataAccessException {
		SubscriberSubscriptions sds= null;
		if(rs.next()){
			sds = new SubscriberSubscriptions();
			sds.setDataSourceID(rs.getString("DATASOURCE_ID"));
			sds.setGraphID(rs.getString("GRAPH_ID"));
			sds.setSubscriberDataSourceID(rs.getString("SUBSCRIBER_DATASOURCE_ID"));
			sds.setSubscriberID(rs.getString("SUBSCRIBER_ID"));
		}
		return sds;
	}

}
