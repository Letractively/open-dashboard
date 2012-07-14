package com.odb.view.dashboard.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.odb.view.dashboard.client.dto.ViewSettings;
import com.odb.view.dashboard.client.exceptions.GraphNotAvailableException;
import com.odb.view.dashboard.client.exceptions.ViewSettingNotConsistentException;


/**
 * The client side stub for the RPC service.<br>
 * 
 * see <a href="http://code.google.com/webtoolkit/doc/latest/tutorial/RPC.html">GWT RPC</a>
 * 
 * @see com.odb.view.dashboard.server.DashboardServiceImpl
 * 
 */
@RemoteServiceRelativePath("dashboardService")
public interface DashboardService extends RemoteService {
	
	/**
	 * Gets the data update.
	 * 
	 * this method is RPC called by client side code to get data update for the chart.
	 *
	 * @param dataSourceId the data source id
	 * @param graphID the graph id
	 * @return the data update of any type extends {@link DataVO} <br> the returned object type is chosen by the graphID
	 * @throws GraphNotAvailableException the graph not available exception
	 */
	DataVO getDataUpdate(String dataSourceId, String graphID) throws GraphNotAvailableException;

	/**
	 * Gets the current user view settings.
	 * 
	 * the user id is known from the HttpSession.
	 *
	 * @return the current view settings
	 * @throws ViewSettingNotConsistentException the view setting not consistent exception
	 * 
	 * @see ViewSettings
	 */
	ViewSettings getCurrentViewSettings() throws ViewSettingNotConsistentException;
	
}
