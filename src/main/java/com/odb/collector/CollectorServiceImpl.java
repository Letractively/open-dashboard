package com.odb.collector;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.odb.core.DataSourceInfo;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.core.service.OpenDashBoard;

/**
 * The Class CollectorServiceImpl.
 * 
 * this class is an implementation for the com.odb.collector.CollectorService endpointInterface
 */
@WebService(endpointInterface="com.odb.collector.CollectorService")
public class CollectorServiceImpl implements CollectorService {

	/** The log. */
	private static Logger log = Logger.getLogger(CollectorServiceImpl.class);
	
	/** The OpenDashBoard core service. */
	private OpenDashBoard odbCore;
	
	/**
	 * Sets the OpenDashBoard core service.
	 *
	 * @param odbCore the new odb core
	 */
	public void setOdbCore(OpenDashBoard odbCore) {
		this.odbCore = odbCore;
	}

	
	/* (non-Javadoc)
	 * @see com.odb.collector.CollectorService#registerDataSource(java.lang.String, com.odb.core.service.DataSourceConfiguration)
	 */
	public String registerDataSource(String pubID,
			DataSourceConfiguration dsConfig) throws RemoteException {
		String dsID = null;
		try {
			dsID = odbCore.registerDataSource(pubID, dsConfig);
		} catch (SQLException e) {
			log.error("error while register Data Source for publisher ID: "+ pubID, e);
		}
		return dsID;
	}

	/* (non-Javadoc)
	 * @see com.odb.collector.CollectorService#addDataSeries(java.lang.String, java.lang.String, com.odb.collector.UserDataWrapper)
	 */
	public void addDataSeries(String pubID, String dsID,
			UserDataWrapper userData) throws RemoteException {
		try {
			odbCore.addDataSeries(pubID, dsID, userData.map);
		} catch (SQLException e) {
			log.error("error while adding Data Series for publisher ID: "+ pubID+" and data source Id:" + dsID, e);
		}
	}


	/* (non-Javadoc)
	 * @see com.odb.collector.CollectorService#getDataSourceInfo(java.lang.String)
	 */
	public DataSourceInfo getDataSourceInfo(String datasourceId)
			throws RemoteException {
		try {
			return odbCore.getDataSourceInfo(datasourceId);
		} catch (SQLException e) {
			log.error("error while get DataSourceInfo for datasourceId: "+ datasourceId, e);
		}
		return null;
	}


}
