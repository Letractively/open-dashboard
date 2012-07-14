package com.odb.view.dashboard.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.odb.view.dashboard.client.dto.ViewSettings;


/**
 * The async counterpart of <code>DashboardService</code>.
 */
public interface DashboardServiceAsync {

	/**
	 * Gets the data update.
	 *
	 * @param dataSourceId the data source id
	 * @param graphID the graph id
	 * @param callback the callback
	 * @return the data update
	 */
	void getDataUpdate(String dataSourceId, String graphID, AsyncCallback<DataVO> callback);

	/**
	 * Gets the current view settings.
	 *
	 * @param callback the callback
	 * @return the current view settings
	 */
	void getCurrentViewSettings(AsyncCallback<ViewSettings> callback);
}
