package com.odb.publisher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import org.apache.log4j.Logger;

import com.odb.collector.UserDataWrapperMapEntry;
import com.odb.publisher.dto.SeriesJob;

public class DatabaseExecutor implements JobSeriesExecuter {
	private Connection jdbcConnection;
	private static Logger log = Logger.getLogger(DatabaseExecutor.class);
	String dataSourceID;
	public DatabaseExecutor(String dsID) {
		dataSourceID = dsID;
	}

	private Connection getJDBCConnection(HashMap<String, String> attr) {
		// log.info("Datasource : " + datasource.getDatasourceId() +
		// " trying to get a database connection.");
		try {
			Class.forName(attr.get("driver"));
			return DriverManager.getConnection(attr.get("url") + "/" + attr.get("dbName"), attr.get("username"), attr.get("password"));
		} catch (ClassNotFoundException e) {
			log.error("Could not establish the Database Connection for the datasource \"" + "\". please make sure the JDBC driver in the classpath", e);
		} catch (SQLException e) {
			log.error("Could not establish the Database the Connection URL: " + attr.get("url") + "/" + attr.get("dbName") + " for the datasource \""
					+ dataSourceID + "\". Please recheck your Connection properties. ", e);
		} catch (Exception e) {
			log.error("Could not establish the Database Connection for the datasource \"" + dataSourceID + "\" ", e);
		}
		return null;
	}
	public Double jobExecutor(SeriesJob seriesJob) {
		Double result = null;
		jdbcConnection = getJDBCConnection(seriesJob.getJobAttributes());
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (jdbcConnection != null) {
			try {
				statement = jdbcConnection.prepareStatement(seriesJob.getJob());
				resultSet = statement.executeQuery();
				resultSet.next();
				result = new Double(resultSet.getDouble(1));
				resultSet.close();
			} catch (SQLException e) {
				log.error("Error while interacting with database for the datasource \"" + dataSourceID
						+ "\". Please recheck your SQL statement. ", e);
			} catch (Exception e) {
				log.error("Error while calling the Web Service CollectorService.addDataSeries for datasource \"" + dataSourceID + "\".",
						e);
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (jdbcConnection != null) {
						jdbcConnection.close();
					}
				} catch (SQLException e) {
					log.error("SQLException", e);
				}
			}
		}
		return result;
	}

}
