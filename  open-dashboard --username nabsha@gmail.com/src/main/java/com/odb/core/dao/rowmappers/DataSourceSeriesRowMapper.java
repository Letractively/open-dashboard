package com.odb.core.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.odb.core.dao.dto.DataSourceSeries;

/**
 * The Class DataSourceSeriesRowMapper.
 */
public class DataSourceSeriesRowMapper implements RowMapper<DataSourceSeries> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public DataSourceSeries mapRow(ResultSet rs, int rowNum) throws SQLException {
		DataSourceSeries dsSeries = new DataSourceSeries();

		dsSeries.setDataSourceID(rs.getString("DATASOURCE_ID"));
		dsSeries.setSeriesIndex(rs.getLong("DATASOURCE_SERIES_INDEX"));
		dsSeries.setSeriesIndexSeq(rs.getLong("DATASOURCE_SERIES_INDEX_SEQ"));
		dsSeries.setSeriesIndexSeqVal(rs.getDouble("DATASOURCE_SERIES_IDX_SEQ_VAL"));
		return dsSeries;
	}

}
