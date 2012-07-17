/**
 * 
 */
package com.odb.view.dashboard.client;

import java.util.ArrayList;
import java.util.List;

import com.odb.view.dashboard.client.charts.LiveChart;
import com.odb.view.dashboard.client.charts.ODBChart;
import com.odb.view.dashboard.client.dto.DataSourceAxisDetailInfo;
import com.odb.view.dashboard.client.dto.DataSourceAxisInfo;
import com.odb.view.dashboard.client.dto.DataSourceInfo;
import com.odb.view.dashboard.client.dto.SubscriberDataSource;
import com.odb.view.dashboard.client.dto.ViewConfig;
import com.odb.view.dashboard.client.dto.ViewSettings;
import com.odb.view.dashboard.client.exceptions.ChartSettingsNotValidException;

/**
 * A factory for creating Chart objects. 
 *
 */
public class ChartFactory {

	/**
	 * Gets the chart.
	 * 
	 * the chart type will be determined by the {@link SubscriberDataSource#getGraphID()}.
	 * the {@link DataSourceInfo} and a list of {@link DataSourceAxisInfo} used to personalise the chart setting to the 
	 * specified data source
	 *
	 * @param viewSettings the view settings
	 * @param viewConfig the view configuration
	 * @return the chart of type {@link ODBChart}
	 * @throws ChartSettingsNotValidException if the chart settings not valid.
	 * 
	 * @see ODBChart
	 */
	public static ODBChart getChart(DataSourceInfo dataSourceInfo, ArrayList<DataSourceAxisInfo> dataSourceAxisInfoList, int seriesCount) throws ChartSettingsNotValidException{
		//ODBChart chart = null;
		//SubscriberDataSource subscriberDataSource = (SubscriberDataSource) viewSettings.viewConfigMap.get("subscriberDataSource_"+viewConfig.getViewLocationID());
		//DataSourceInfo dataSourceInfo = (DataSourceInfo) viewSettings.viewConfigMap.get("dataSourceInfo_"+viewConfig.getViewLocationID());
		@SuppressWarnings("unchecked")
		//ArrayList<DataSourceAxisInfo> dataSourceAxisInfoList = (ArrayList<DataSourceAxisInfo>) viewSettings.viewConfigMap.get("dataSourceAxisInfoList_"+viewConfig.getViewLocationID());
		//Integer graphID = Integer.valueOf(subscriberDataSource.getGraphID());
		ODBChart chart = constructLiveChart(dataSourceInfo, dataSourceAxisInfoList, seriesCount);
		return chart;
	}
	
	/**
	 * Construct live chart.
	 *
	 * @param dataSourceInfo the data source info
	 * @param dataSourceAxisInfoList the data source axis info list
	 * @return the mobily chart
	 * @throws ChartSettingsNotValidException the chart settings not valid exception
	 */
	private static ODBChart constructLiveChart(DataSourceInfo dataSourceInfo, ArrayList<DataSourceAxisInfo> dataSourceAxisInfoList, int seriesCount) throws ChartSettingsNotValidException{
		DataSourceAxisInfo dataSourceAxisInfo = null;
		Integer min, max, minIndex, maxIndex;
		try {
			for (DataSourceAxisInfo dsai : dataSourceAxisInfoList) {
				if("Y".equals(dsai.getDataSourceAxisType())){
					dataSourceAxisInfo = dsai;
				}
			}
			List<DataSourceAxisDetailInfo> dataSourceAxisDetailInfoList = dataSourceAxisInfo.getDataSourceAxisDetailInfoList();
			min = null;
			max = null;
			minIndex = null;
			maxIndex = null;
			for (DataSourceAxisDetailInfo dataSourceAxisDetailInfo : dataSourceAxisDetailInfoList) {
				if(minIndex == null || dataSourceAxisDetailInfo.getAxisLabelIndex() < minIndex){
					minIndex = dataSourceAxisDetailInfo.getAxisLabelIndex();
					min = Integer.parseInt(dataSourceAxisDetailInfo.getAxisLabelValue());
				}
				if(maxIndex == null || dataSourceAxisDetailInfo.getAxisLabelIndex() > maxIndex){
					maxIndex = dataSourceAxisDetailInfo.getAxisLabelIndex();
					max = Integer.parseInt(dataSourceAxisDetailInfo.getAxisLabelValue());
				}
			}
		} catch (Exception e) {
			throw new ChartSettingsNotValidException("The data Source Axis Setting is not valied for Live Chart, please contact your admin");
		}
		return new LiveChart(dataSourceInfo.getTimeoutInterval(), min, max, dataSourceAxisInfo.getDataSourceAxisName(), seriesCount);
	}
}
