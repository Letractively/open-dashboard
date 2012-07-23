package com.odb.publisher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import com.odb.collector.AxisInfo;
import com.odb.collector.CollectorService;
import com.odb.collector.CollectorServiceImplService;
import com.odb.collector.CollectorServiceProxy;
import com.odb.collector.DataSourceConfiguration;
import com.odb.collector.DataSourceInfo;
import com.odb.collector.InquiryServiceProxy;
import com.odb.collector.PublisherInfo;
import com.odb.collector.RegisterServiceProxy;
import com.odb.publisher.dto.AxisDetails;
import com.odb.publisher.dto.DataConfiguration;
import com.odb.publisher.dto.DataGeneration;
import com.odb.publisher.dto.DataSourceDetails;
import com.odb.publisher.dto.Publisher;
import com.odb.publisher.dto.SeriesJob;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Check publisher ID if it does not exist Check if publisher ID is valid on
 * server If publisher invalid
 */
public class App {
	private static Logger log = Logger.getLogger(App.class);
	ArrayList<DataSourceConfiguration> dsConfigList;
	Properties prop = new Properties();
	Publisher ps;

	private RegisterServiceProxy regWS;
	private InquiryServiceProxy inqWS;
	private XStream xs;

	public App() {
		try {
			prop.load(new FileInputStream("src/main/resources/config.props"));
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
		regWS = new RegisterServiceProxy(prop.getProperty("odbserverurl") + "/collector/registerService");
		inqWS = new InquiryServiceProxy(prop.getProperty("odbserverurl") + "/collector/inquiryService");
		xs = new XStream();
		xs.alias("Publisher", Publisher.class);
		xs.alias("DataSourceDetails", DataSourceDetails.class);
		xs.alias("AxisDetail", AxisDetails.class);
		xs.alias("SeriesJob", SeriesJob.class);
		ps = new Publisher();
		// dsConfigList = new ArrayList<DataSourceConfiguration>();
	}

	public boolean isAlreadyPublisher() {
		if (ps.getPublisherID() == null) {
			System.out.println("Publisher ID does not exist in configuration... requesting publisherID from server");
			return false;
		}

		PublisherInfo pInfo;
		try {
			pInfo = inqWS.getPublisherInfo(ps.getPublisherID());
			if (pInfo != null) {
				log.info("Publisher already registered with server with publisherID=" + pInfo.getPublisherID());
				return true;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean registerPublisher(String name) {
		String publisherID = null;
		try {
			publisherID = regWS.registerPublisher(name);
			log.info("New Publisher Registered with publisherID=" + publisherID);
			ps.setPublisherID(publisherID);
			storeConfig(ps);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	String menu[] = { "0. Exit", "1. Get Registered Datasources", "2. Add Datasource" };

	public int showMenu() {
		String in = "0";
		for (String s : menu) {
			System.out.println(s);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			in = br.readLine();
			if (!isValidSelection(in)) {
				return -1;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(in);
	}

	private boolean isValidSelection(String s) {
		try {
			Integer i = Integer.parseInt(s);
			if (i >= 0 || i < menu.length) {
				return true;
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid selection...");
		}
		return false;
	}

	public void getAllDataSources() {
		DataSourceConfiguration dsC[] = null;
		if (dsConfigList == null) {
			try {
				dsC = inqWS.getAllDataSourceByPublisherID(ps.getPublisherID());
				if (dsC != null) {
					dsConfigList = new ArrayList<DataSourceConfiguration>(Arrays.asList(dsC));
				}
			} catch (RemoteException e) {
				log.error("Failed in getting All data sources");
			}
		}
		if (dsConfigList != null) {
			for (DataSourceConfiguration dsConfig : dsConfigList) {
				System.out.println(dsConfig.getDsName());
			}
		} else {
			System.out.println("No DataSoruce Entry Found...");
		}
	}

	public void checkPublisher() {
		String in = null;
		System.out.println("Check & Validating Publisher ID");

		if (!isAlreadyPublisher()) {
			System.out.println("Registering New Publisher...");
			System.out.println("Enter Publisher Name:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				in = br.readLine();
				registerPublisher(in);
			} catch (IOException e) {
				log.error("Error Checking Publisher ID" + e);
			}
		}
		System.out.println("Registered Publisher... PublisherID=" + ps.getPublisherID());

	}

	public String addDataSource(DataConfiguration dc) {
		DataSourceConfiguration dsConfig = new DataSourceConfiguration();
		String dsID;
		try {
			dsConfig.setPublisherID(ps.getPublisherID());
			dsConfig.setDsName(dc.getDataSourceName());
			dsConfig.setDsTimeoutInterval((long) dc.getTimeoutInterval());
			dsConfig.setSeriesCount(dc.getNumberOfSeries());
			int axisCount = dc.getAxisDetails().size();
			AxisInfo[] xsInfoList = new AxisInfo[axisCount];
			for (int i = 0; i < axisCount; i++) {
				xsInfoList[i] = new AxisInfo();
				ArrayList<String> axisLabels = new ArrayList<String>();
				xsInfoList[i].setDataSourceAxisName(dc.getAxisDetails().get(i).getAxisName());
				axisLabels.add(new Integer((int) dc.getAxisDetails().get(i).getMinima()).toString());
				axisLabels.add(new Integer((int) dc.getAxisDetails().get(i).getMaxima()).toString());
				xsInfoList[i].setAxisLabels(axisLabels.toArray(new String[axisLabels.size()]));
				xsInfoList[i].setDataSourceAxisType("Y");
			}
			dsConfig.setXsInfo(xsInfoList);
			System.out.println(dsConfig);
			dsID = regWS.registerDataSource(dsConfig);
			System.out.println("Datasource ID = " + dsID);
			return dsID;
		} catch (IOException e) {
			log.error("Error AddDatasource for  Publisher ID:" + ps.getPublisherID() + e);
		}
		return null;
	}

	private Publisher loadConfig() {
		try {
			return (Publisher) xs.fromXML(new FileInputStream("src/main/resources/publisherConfiguration.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void storeConfig(Publisher publisher) {
		try {
			xs.toXML(publisher, new FileOutputStream("src/main/resources/publisherConfiguration.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Publisher reloadConfig(Publisher publisher) {
		storeConfig(publisher);
		return loadConfig();
	}

	private void checkDataSources() {
		for (DataSourceDetails ds : ps.getDataSources()) {
			if (ds.getDataSourceID().isEmpty()) {
				ds.setDataSourceID(addDataSource(ds.getDataConfig()));
				storeConfig(ps);
			} else { // Check if the presented data source is valid
				try {
					DataSourceInfo dsInfo = inqWS.getDataSourceInfo(ds.getDataSourceID());
					if (dsInfo == null) {
						log.warn("Invalid DataSourceID:" + ds.getDataSourceID() + " in the configuration file. Re-registering dataSource");
						ds.setDataSourceID(addDataSource(ds.getDataConfig()));
						storeConfig(ps);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void startDataPublish() {
		for (DataSourceDetails ds : ps.getDataSources()) {
			DataPublisherThread dp = new DataPublisherThread(ps.getPublisherID(), ds);
			dp.start();
		}
	}

	public static void main(String[] args) {

		App a = new App();
		a.ps = a.loadConfig();

		System.out.println("Validating Publisher Configuration...");
		a.checkPublisher();
		a.checkDataSources();
		a.startDataPublish();
	}
}
