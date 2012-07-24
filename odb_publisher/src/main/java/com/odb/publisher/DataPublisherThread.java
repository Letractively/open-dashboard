package com.odb.publisher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.odb.collector.CollectorServiceProxy;
import com.odb.collector.InquiryServiceProxy;
import com.odb.collector.UserDataWrapper;
import com.odb.collector.UserDataWrapperMapEntry;
import com.odb.publisher.dto.DataGeneration;
import com.odb.publisher.dto.DataSourceDetails;
import com.odb.publisher.dto.SeriesJob;

public class DataPublisherThread extends Thread {
	DataSourceDetails dsDetails;
	private static Logger log = Logger.getLogger(DataPublisherThread.class);
	private String publisherID;
	private Connection jdbcConnection;
	private CollectorServiceProxy collWS;
	Properties prop = new Properties();

	DataPublisherThread(String publisherID, DataSourceDetails dS) {
		try {
			prop.load(new FileInputStream("config/config.props"));
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.publisherID = publisherID;
		this.dsDetails = dS;
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
					+ dsDetails.getDataSourceID() + "\". Please recheck your Connection properties. ", e);
		} catch (Exception e) {
			log.error("Could not establish the Database Connection for the datasource \"" + dsDetails.getDataSourceID() + "\" ", e);
		}
		return null;
	}

	public void run() {
		DataGeneration dataGen = dsDetails.getDataGen();
		ArrayList<SeriesJob> sj = dataGen.getSeriesJob();

		collWS = new CollectorServiceProxy(prop.getProperty("odbserverurl") + "/collector/collectorService");

		while (true) {
			UserDataWrapperMapEntry[] mapEntry = new UserDataWrapperMapEntry[dataGen.getSeriesJob().size()];
			UserDataWrapper wrapper = new UserDataWrapper(mapEntry);
			for (int j = 0; j < dataGen.getSeriesJob().size(); j++) {
				jdbcConnection = getJDBCConnection(dataGen.getSeriesJob().get(j).getJobAttributes());
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				if (jdbcConnection != null) {
					try {
						statement = jdbcConnection.prepareStatement(dataGen.getSeriesJob().get(j).getJob());
						resultSet = statement.executeQuery();
						resultSet.next();
						mapEntry[j] = new UserDataWrapperMapEntry();
						mapEntry[j].setKey((long) j + 1);
						mapEntry[j].setValue(resultSet.getDouble(1));
						resultSet.close();
					} catch (SQLException e) {
						log.error("Error while interacting with database for the datasource \"" + dsDetails.getDataSourceID()
								+ "\". Please recheck your SQL statement. ", e);
					} catch (Exception e) {
						log.error("Error while calling the Web Service CollectorService.addDataSeries for datasource \"" + dsDetails.getDataSourceID() + "\".",
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
			}
			wrapper.setMap(mapEntry);
			try {
				collWS.addDataSeries(publisherID, dsDetails.getDataSourceID(), wrapper);
				Thread.sleep(dataGen.getJobFrequency());
			} catch (InterruptedException e) {
				log.info("", e);
				break;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				wrapper = null;
			}
		}
	}
}
