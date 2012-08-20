/**
 * 
 */
package com.odb.view.dashboard.client;

import java.util.ArrayList;
import java.util.List;

import com.odb.core.service.AxisInfo;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.view.dashboard.client.charts.DynamicLineChart;
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
	 * the chart type will be determined by the
	 * {@link SubscriberDataSource#getGraphID()}. the {@link DataSourceInfo} and
	 * a list of {@link DataSourceAxisInfo} used to personalise the chart
	 * setting to the specified data source
	 * 
	 * @param viewSettings
	 *            the view settings
	 * @param viewConfig
	 *            the view configuration
	 * @return the chart of type {@link ODBChart}
	 * @throws ChartSettingsNotValidException
	 *             if the chart settings not valid.
	 * 
	 * @see ODBChart
	 */
	public static ODBChart getChart(DataSourceConfiguration dsConfig, ArrayList<DataVO> dataList) throws ChartSettingsNotValidException {
		// ODBChart chart = null;
		// SubscriberDataSource subscriberDataSource = (SubscriberDataSource)
		// viewSettings.viewConfigMap.get("subscriberDataSource_"+viewConfig.getViewLocationID());
		// DataSourceInfo dataSourceInfo = (DataSourceInfo)
		// viewSettings.viewConfigMap.get("dataSourceInfo_"+viewConfig.getViewLocationID());
		@SuppressWarnings("unchecked")
		// ArrayList<DataSourceAxisInfo> dataSourceAxisInfoList =
		// (ArrayList<DataSourceAxisInfo>)
		// viewSettings.viewConfigMap.get("dataSourceAxisInfoList_"+viewConfig.getViewLocationID());
		// Integer graphID = Integer.valueOf(subscriberDataSource.getGraphID());
		ArrayList<TimeSeriesDataVO> dataListLive = new ArrayList<TimeSeriesDataVO>();
		for (DataVO d : dataList) {
			dataListLive.add((TimeSeriesDataVO) d);
		}
		ODBChart chart = constructLiveChart(dsConfig, dataListLive);
		return chart;
	}

	/**
	 * Construct live chart.
	 * 
	 * @param dataSourceInfo
	 *            the data source info
	 * @param dataSourceAxisInfoList
	 *            the data source axis info list
	 * @return the mobily chart
	 * @throws ChartSettingsNotValidException
	 *             the chart settings not valid exception
	 */
	private static ODBChart constructLiveChart(DataSourceConfiguration dsConfig, ArrayList<TimeSeriesDataVO> dataList) throws ChartSettingsNotValidException {
		AxisInfo dataSourceAxisInfo = null;
		DynamicLineChart liveChart = null;
		Integer min, max, minIndex, maxIndex;
		try {
			for (AxisInfo dsai : dsConfig.getXsInfo()) {
				if ("Y".equals(dsai.getDataSourceAxisType())) {
					dataSourceAxisInfo = dsai;
				}
			}
			ArrayList<String> axisLabels = dataSourceAxisInfo.getAxisLabels();
			min = Integer.parseInt(axisLabels.get(0));
			max = Integer.parseInt(axisLabels.get(axisLabels.size() - 1));
		} catch (Exception e) {
			throw new ChartSettingsNotValidException("The data Source Axis Setting is not valied for Live Chart, please contact your admin");
		}
		// liveChart =new LiveChart(dsConfig.getDsTimeoutInterval(), min, max,
		// dataSourceAxisInfo.getDataSourceAxisName(),
		// dsConfig.getSeriesCount(),dataList);
		liveChart = new DynamicLineChart(dsConfig.getSeriesCount(), min, max, dataList);
		return liveChart;
	}
}
