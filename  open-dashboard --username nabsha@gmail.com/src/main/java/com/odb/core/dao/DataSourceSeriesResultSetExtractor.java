package com.odb.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.odb.core.DataSourceSeries;

/**
 * The Class DataSourceSeriesResultSetExtractor.
 */
public class DataSourceSeriesResultSetExtractor implements ResultSetExtractor<DataSourceSeries> {

	

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	public DataSourceSeries extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		DataSourceSeries dsSeries = new DataSourceSeries();
		if(rs.next()){
			dsSeries.setDataSourceID(rs.getString("DATASOURCE_ID"));
			dsSeries.setSeriesIndex(rs.getLong("DATASOURCE_SERIES_INDEX"));
			dsSeries.setSeriesIndexSeq(rs.getLong("DATASOURCE_SERIES_INDEX_SEQ"));
			dsSeries.setSeriesIndexSeqVal(rs.getDouble("DATASOURCE_SERIES_IDX_SEQ_VAL"));
		}
		return dsSeries;
	}

}
