package com.odb.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.odb.core.SubscriberInfo;

/**
 * The Class SubscriberInfoResultSetExtractor.
 */
public class SubscriberInfoResultSetExtractor implements ResultSetExtractor<SubscriberInfo> {

	

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	public SubscriberInfo extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		SubscriberInfo subInfo = null;
		if(rs.next()){
			subInfo = new SubscriberInfo();
			subInfo.setSubscriberID(rs.getString("SUBSCRIBER_ID"));
			subInfo.setSubscriberName(rs.getString("SUBSCRIBER_NAME"));
			subInfo.setSubscriberPassword(rs.getString("SUBSCRIBER_PASSWORD"));
		}
		return subInfo;
	}

}
