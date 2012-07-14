package com.odb.collector;

import java.sql.SQLException;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.odb.core.service.OpenDashBoard;

/**
 * The Class RegisterServiceImpl.
 * 
 * this class is an implementation for the com.odb.collector.RegisterService endpointInterface
 */
@WebService(endpointInterface = "com.odb.collector.RegisterService")
public class RegisterServiceImpl implements RegisterService {

	/** The log. */
	private static Logger log = Logger.getLogger(RegisterServiceImpl.class);
	
	/** The OpenDashBoard core service.  */
	private OpenDashBoard odbCore;
	
	/**
	 * Sets the OpenDashBoard core service. 
	 *
	 * @param odbCore the new OpenDashBoard core service. 
	 */
	public void setOdbCore(OpenDashBoard odbCore) {
		this.odbCore = odbCore;
	}

	/* (non-Javadoc)
	 * @see com.odb.collector.RegisterService#registerPublisher(java.lang.String)
	 */
	public String registerPublisher(String publisherName) {
    	
		String pubID = null;
		try {
			pubID = odbCore.registerPublisher(publisherName);
		} catch (SQLException e) {
			log.error("error while register Publisher name: "+ publisherName, e);
		}
		return pubID;
	}

	

}
