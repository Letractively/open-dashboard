/**
 * 
 */
package com.odb.view.dashboard.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * The Class ViewSettings.
 * 
 * has all needed configuration for the current login user.<br> it is used to draw the Chart view dynamically from user settings in database 
 *
 */
@SuppressWarnings("serial")
public class ViewSettings implements Serializable {

	/** The view config list. */
	public ArrayList<ViewConfig> viewConfigList;
	
	/** The subscriber info. */
	public SubscriberInfo subscriberInfo;
	
	/** this map should have the following Objects for each active view config: <p> 
	 * {@link SubscriberDataSource} with key name subscriberDataSource_${ViewConfigId} <br>
	 * {@link DataSourceInfo} with key name dataSourceInfo_${ViewConfigId} <br>
	 * {@link DataSourceAxisInfo} List with key name dataSourceAxisInfoList_${ViewConfigId}. 
	 * 
	 */
	public HashMap<String, Serializable> viewConfigMap;
}
