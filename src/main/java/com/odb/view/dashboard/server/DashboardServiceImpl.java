package com.odb.view.dashboard.server;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.odb.core.DataSourceAxisDetailInfo;
import com.odb.core.DataSourceInfo;
import com.odb.core.DataSourceSeries;
import com.odb.core.SubscriberDataSource;
import com.odb.core.SubscriberInfo;
import com.odb.core.ViewConfiguration;
import com.odb.core.service.OpenDashBoard;
import com.odb.view.dashboard.client.DashboardService;
import com.odb.view.dashboard.client.DataVO;
import com.odb.view.dashboard.client.dto.DataSourceAxisInfo;
import com.odb.view.dashboard.client.dto.LiveChartVO;
import com.odb.view.dashboard.client.dto.ViewConfig;
import com.odb.view.dashboard.client.dto.ViewSettings;
import com.odb.view.dashboard.client.exceptions.GraphNotAvailableException;
import com.odb.view.dashboard.client.exceptions.ViewSettingNotConsistentException;
import com.odb.view.util.Utilities;

/**
 * The server side implementation of the RPC service.<br>
 * 
 * this class is of type RemoteServiceServlet -i.e it is a servlet at the end- and hence, it is initialised by the {@link #init(javax.servlet.ServletConfig)} method.<br>
 * by ServletConfig object we can get a reference to Spring WebApplicationContext, and hence, the OpenDashBoardCore service.
 */
@SuppressWarnings("serial")
public class DashboardServiceImpl extends RemoteServiceServlet implements
		DashboardService {
	
	/** The log. */
	private static Logger log = Logger.getLogger(DashboardServiceImpl.class);
	
	/** The odb core. */
	OpenDashBoard odbCore;
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(javax.servlet.ServletConfig config) throws javax.servlet.ServletException {
		super.init(config);
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		odbCore = (OpenDashBoard) context.getBean("OpenDashBoardCore");
	};

	/* (non-Javadoc)
	 * @see com.mobily.dashboard.client.DashboardService#getDataUpdate(java.lang.String, java.lang.String)
	 */
	public DataVO getDataUpdate(String dataSourceId, String graphID) throws GraphNotAvailableException {
		int rowNum = 1;
		if("1".equals(graphID)){
			rowNum = 3;
		}
		List<DataSourceSeries> series=odbCore.getLatestSeriesData(dataSourceId, rowNum);
		if("1".equals(graphID)){
			return new LiveChartVO(new Date(), series.get(0).getSeriesIndexSeqVal(), series.get(1).getSeriesIndexSeqVal(), series.get(2).getSeriesIndexSeqVal() );
		}
		throw new GraphNotAvailableException();
	}

	/* (non-Javadoc)
	 * @see com.mobily.dashboard.client.DashboardService#getCurrentViewSettings()
	 */
	public ViewSettings getCurrentViewSettings() throws ViewSettingNotConsistentException{
		ViewSettings viewSettings = new ViewSettings();
		//getting Current subscriber info...
		SubscriberInfo subscriberInfo = (SubscriberInfo) getThreadLocalRequest().getSession().getAttribute("subscriberInfo");
		viewSettings.subscriberInfo = Utilities.getClientSubscriberInfo(subscriberInfo);
		try {
			//getting system active ViewConfiguration
			List<ViewConfiguration> viewConfigurationList = odbCore.getViewConfigurationList();
			ArrayList<ViewConfig> clientViewConfigList = new ArrayList<ViewConfig>(viewConfigurationList.size());
			for(ViewConfiguration viewConfiguration:viewConfigurationList){
				clientViewConfigList.add(Utilities.getClientViewConfig(viewConfiguration));
			}
			viewSettings.viewConfigList=clientViewConfigList;
			viewSettings.viewConfigMap = new java.util.HashMap<String, Serializable>();
			for(ViewConfig viewConfig :clientViewConfigList){
				//getting SubscriberDataSource
				SubscriberDataSource subscriberDataSource = odbCore.getSubscriberDataSourceBy(subscriberInfo.getSubscriberID(), viewConfig.getViewLocationID());
				//adding SubscriberDataSource to map...
				viewSettings.viewConfigMap.put("subscriberDataSource_"+viewConfig.getViewLocationID(), Utilities.getClientSubscriberDataSource((subscriberDataSource)));
				//getting DataSourceInfo
				DataSourceInfo dataSourceInfo = odbCore.getDataSourceByDataSourceID(subscriberDataSource.getDataSourceID());
				//adding DataSource to map...
				viewSettings.viewConfigMap.put("dataSourceInfo_"+viewConfig.getViewLocationID(), Utilities.getClientDataSourceInfo((dataSourceInfo)));
				//getting DataSourceAxisInfo
				List<com.odb.core.DataSourceAxisInfo> dataSourceAxisInfoList = odbCore.getDataSourceAxisInfo(dataSourceInfo.getDataSourceID());
				ArrayList<DataSourceAxisInfo> clientDataSourceAxisInfoList = new ArrayList<DataSourceAxisInfo>(dataSourceAxisInfoList.size());
				for(com.odb.core.DataSourceAxisInfo dataSourceAxisInfo:dataSourceAxisInfoList){
					//getting axis detail config
					List<DataSourceAxisDetailInfo> dataSourceAxisDetailInfoList = odbCore.getDataSourceAxisDetailInfoListBy(dataSourceAxisInfo.getDataSourceAxisID());
					clientDataSourceAxisInfoList.add(Utilities.getClientDataSourceAxisInfo(dataSourceAxisInfo, dataSourceAxisDetailInfoList));
				}
				viewSettings.viewConfigMap.put("dataSourceAxisInfoList_"+viewConfig.getViewLocationID(), clientDataSourceAxisInfoList);
			}
		} catch (SQLException e) {
			log.error("Error while getting ViewSettings for subscriber ID: "+subscriberInfo.getSubscriberID(), e);
			throw new ViewSettingNotConsistentException("Error while getting ViewSettings");
		} catch (DataAccessException e){
			log.error("Error while getting ViewSettings for subscriber ID: "+subscriberInfo.getSubscriberID(), e);
			throw new ViewSettingNotConsistentException("Error while getting ViewSettings");
		}
		return viewSettings;
	}
}
