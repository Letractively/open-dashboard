package com.odb.view.dashboard.client.charts;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.odb.view.dashboard.client.DataVO;


/**
 * The Class ODBChart.
 * 
 * it is the parent for all Dashboard chart types.<br>
 * Any chart object should extend this object and implement the abstract methods like {@link #asWidget()} and {@link #updateChartData(DataVO, Label)}
 * 
 * @see com.odb.view.dashboard.client.charts.LiveChart
 */
public abstract class ODBChart {

	/**
	 * As widget.
	 * 
	 * Retrieve the <a href="http://www.sencha.com/products/extgwt">EXT GWT</a> Chart to a GWT {@link Widget} that is ready to be displayed inside a pannel.
	 *
	 * @return the widget
	 */
	public abstract Widget asWidget(); 
	
	/**
	 * Update chart data.
	 * 
	 * for each chart type, this method will be implemented. as every chart knows how to update its own data. 
	 *
	 * @param data the data
	 * @param errorLabel Every chart implementation, knows when it should display error to the user and when it is not.
	 */
	public abstract void updateChartData(DataVO data, Label errorLabel); 
}
