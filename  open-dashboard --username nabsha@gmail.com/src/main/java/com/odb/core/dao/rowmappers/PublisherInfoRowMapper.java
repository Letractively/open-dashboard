package com.odb.core.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.odb.core.dao.dto.PublisherInfo;

/**
 * The Class PublisherInfoRowMapper.
 */
public class PublisherInfoRowMapper implements RowMapper<PublisherInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public PublisherInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		PublisherInfo pInfo = new PublisherInfo();
		pInfo.setPublisherID(rs.getString("PUBLISHER_ID"));
		pInfo.setPublisherName(rs.getString("PUBLISHER_NAME"));
		return pInfo;
	}

}
