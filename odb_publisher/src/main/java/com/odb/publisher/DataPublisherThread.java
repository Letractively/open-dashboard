package com.odb.publisher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.odb.collector.CollectorServiceProxy;
import com.odb.collector.UserDataWrapper;
import com.odb.collector.UserDataWrapperMapEntry;
import com.odb.publisher.dto.DataGeneration;
import com.odb.publisher.dto.DataSourceDetails;
import com.odb.publisher.dto.SeriesJob;

public class DataPublisherThread extends Thread {
	DataSourceDetails dsDetails;
	private static Logger log = Logger.getLogger(DataPublisherThread.class);
	private String publisherID;
	private CollectorServiceProxy collWS;
	private Properties prop = new Properties();
	private DataGeneration dataGen = null;

	DataPublisherThread(String publisherID, DataSourceDetails dS) {
		try {
			prop.load(new FileInputStream("config/config.props"));
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

	
	
	public void run() {
		dataGen = dsDetails.getDataGen();
		ArrayList<SeriesJob> sj = dataGen.getSeriesJob();

		collWS = new CollectorServiceProxy(prop.getProperty("odbserverurl") + "/collector/collectorService");

		while (true) {
			UserDataWrapperMapEntry[] mapEntry = new UserDataWrapperMapEntry[dataGen.getSeriesJob().size()];
			UserDataWrapper wrapper = new UserDataWrapper(mapEntry);
			for (int j = 0; j < dataGen.getSeriesJob().size(); j++) {
				JobSeriesExecuter jobExec = null;
				mapEntry[j] = new UserDataWrapperMapEntry();
				mapEntry[j].setKey((long)j + 1);
				if (dataGen.getSeriesJob().get(j).getJobSourceType().equalsIgnoreCase("sql")) {
					jobExec = new DatabaseExecutor(dsDetails.getDataSourceID());
				} else  if(dataGen.getSeriesJob().get(j).getJobSourceType().equalsIgnoreCase("sysmon")) {
					jobExec = new SysmonExecutor(dsDetails.getDataSourceID());
				}
				mapEntry[j].setValue(jobExec.jobExecutor(dataGen.getSeriesJob().get(j)));
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
