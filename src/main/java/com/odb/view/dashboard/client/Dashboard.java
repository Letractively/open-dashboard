package com.odb.view.dashboard.client;

import java.util.Date;

import org.icepush.gwt.client.GWTPushContext;
import org.icepush.gwt.client.PushEventListener;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.odb.view.core.ActionProcessor;
import com.odb.view.dashboard.client.charts.ODBChart;
import com.odb.view.dashboard.client.dto.DataSourceInfo;
import com.odb.view.dashboard.client.dto.SubscriberDataSource;
import com.odb.view.dashboard.client.dto.ViewConfig;
import com.odb.view.dashboard.client.dto.ViewSettings;
import com.odb.view.dashboard.client.exceptions.ChartSettingsNotValidException;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.fx.client.Resizable;
import com.sencha.gxt.theme.base.client.panel.FramedPanelAppearance;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;


/**
 * Dashboard class is the GWT Entry point classes that define <code>onModuleLoad()</code>.
 * <br>
 * The class  uses the {@link DashboardService#getCurrentViewSettings()} to load the user preferences on load time
 * and once the settings is loaded, it begins to draw the {@link ODBChart} for each graph 
 * <p>
 * The class registers a {@link PushEventListener} listener to listen to the events fired by the {@link ActionProcessor#firePushEventAction(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
 * <br>
 * Once the event is fired, the {@link DashboardService#getDataUpdate(String, String)} get called. and the chart the updated with the {@link ODBChart#updateChartData(DataVO, Label)}   
 * <p>
 * Note that this class is a part of the client package. And hence, it will be converted to Javascript code
 * in the "GWT compilation" process
 * <p>
 * 
 * see <a href="http://code.google.com/webtoolkit/doc/latest/tutorial/">GWT Tutorial</a> And <a href="http://wiki.icesoft.org/display/PUSH/GWT+Integration">ICEpush GWT Integration</a>
 * 
 * 
 */
public class Dashboard implements EntryPoint {

	/** The dashboard RPC service. */
	private DashboardServiceAsync dashboardService = GWT
			.create(DashboardService.class);
	
	/** The date format. */
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy h:m:s a");
	
	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		
	    
		dashboardService.getCurrentViewSettings(new AsyncCallback<ViewSettings>() {
			
			public void onFailure(Throwable caught) {
				Label errorLabel = new Label();
			    errorLabel.setText(caught.getMessage()+", Please Consult System Support.");
				RootPanel.get("error").add(errorLabel);
			}

			public void onSuccess(ViewSettings viewSettings) {
//				final ContentPanel w = new ContentPanel(GWT.<ContentPanelAppearance> create(FramedPanelAppearance.class));
//				MenuBar mBar = new MenuBar();
//				
//			    Menu file = new Menu();	    
//			    file.add(new MenuItem("New"));
//			    file.add(new MenuItem("Open"));
//			    mBar.add(new MenuBarItem("File", file));
//			    
//			    Menu sub2 = new Menu();
//			    sub2.add(new MenuItem("Cut"));
//			    sub2.add(new MenuItem("Copy"));
//			    mBar.add(new MenuBarItem("Edit", sub2));
//				HBoxLayoutContainer hLayout = new HBoxLayoutContainer();
//				hLayout.add(mBar);
//				w.add(mBar);
				
				for(ViewConfig viewConfig : viewSettings.viewConfigList){
					final DataSourceInfo dataSourceInfo = (DataSourceInfo) viewSettings.viewConfigMap.get("dataSourceInfo_"+viewConfig.getViewLocationID());
					final SubscriberDataSource subscriberDataSource = (SubscriberDataSource) viewSettings.viewConfigMap.get("subscriberDataSource_"+viewConfig.getViewLocationID());
					// the panel that will hold the chart
					ContentPanel panel = new ContentPanel(GWT.<ContentPanelAppearance> create(FramedPanelAppearance.class));
					Draggable d = new Draggable(panel);
					new Resizable(panel); 
					
				    panel.getElement().getStyle().setMargin(2, Unit.PX);
				    // set a header of the panel
				    panel.setHeadingText("Chart "+viewConfig.getViewLocationID());
				    panel.setPixelSize(viewConfig.getViewWidth(), viewConfig.getViewHeight());
				    panel.setBodyBorder(true);
				    panel.setBodyStyleName("white-bg");
				    //error label to display error information
				    final Label errorLabel = new Label();
				    errorLabel.setText("");
//				    w.add(errorLabel);
					RootPanel.get("error_"+viewConfig.getViewLocationID()).add(errorLabel);
					
				    VerticalLayoutContainer layout = new VerticalLayoutContainer();
				    layout.setBorders(true);
				    //table that provide the data source information
				    final FlexTable flexTable = createInfoTable(dataSourceInfo);
				    layout.add(flexTable, new VerticalLayoutData(1, viewConfig.getViewHeight() / 10));
				    
				    try {
						final ODBChart chart = ChartFactory.getChart(viewSettings, viewConfig, dataSourceInfo.getSeriesCount());
						layout.add(chart.asWidget(), new VerticalLayoutData(1, 1));
						
						panel.add(layout);
//						w.add(panel);
						RootPanel.get("chart_"+viewConfig.getViewLocationID()).add(panel);
						//now opening the GWT push context to register the PushEventListener
						GWTPushContext pushContext = GWTPushContext.getInstance();
						pushContext.addPushEventListener(new PushEventListener() {
							/**
							 * this is the callback that will be invoked when a notification is
							 * received for the group that has the name equals to DataSourceID
							 */
							public void onPushEvent() {
								//calls the RPC method on server
								dashboardService.getDataUpdate(dataSourceInfo.getDataSourceID(), subscriberDataSource.getGraphID(), 
										new AsyncCallback<DataVO>() {
									
											public void onFailure(Throwable caught) {
												errorLabel.setText("Error, could not get Data Update for Data Source: "+dataSourceInfo.getDataSourceName()+". Please Contact System Support. ");
											}

											public void onSuccess(DataVO result) {
												chart.updateChartData(result, errorLabel);
												//update the Last Update time with current time
												flexTable.setText(1, 3, dateFormat.format(new Date()));
											}
										});
							}
						}, dataSourceInfo.getDataSourceID());
						
					} catch (ChartSettingsNotValidException e) {
						errorLabel.setText(e.getMessage());
					}
					
				}
//			    RootPanel.get().add(w);
				
			}

			private FlexTable createInfoTable(DataSourceInfo dataSourceInfo) {
				FlexTable table = new FlexTable();
			    table.setText(0, 0, "Publisher ID:");
			    table.getFlexCellFormatter().addStyleName(0, 0, "nameText");
			    table.getFlexCellFormatter().setWidth(0, 0, "20%");
			    table.getFlexCellFormatter().setHorizontalAlignment(0, 0, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(0, 0, false);
			    table.setText(0, 1, dataSourceInfo.getPublisherID());
			    table.getFlexCellFormatter().addStyleName(0, 1, "valueText");
			    table.getFlexCellFormatter().setWidth(0, 1, "30%");
			    table.getFlexCellFormatter().setHorizontalAlignment(0, 1, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(0, 1, false);
			    
			    table.setText(0, 2, "Data Source Name:");
			    table.getFlexCellFormatter().addStyleName(0, 2, "nameText");
			    table.getFlexCellFormatter().setWidth(0, 2, "20%");
			    table.getFlexCellFormatter().setHorizontalAlignment(0, 2, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(0, 2, false);
			    table.setText(0, 3, dataSourceInfo.getDataSourceName());
			    table.getFlexCellFormatter().addStyleName(0, 3, "valueText");
			    table.getFlexCellFormatter().setWidth(0, 3, "30%");
			    table.getFlexCellFormatter().setHorizontalAlignment(0, 3, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(0, 3, false);
			    
			    
			    table.setText(1, 0, "Timeout Interval (milis):");
			    table.getFlexCellFormatter().addStyleName(1, 0, "nameText");
			    table.getFlexCellFormatter().setWidth(1, 0, "20%");
			    table.getFlexCellFormatter().setHorizontalAlignment(1, 0, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(1, 0, false);
			    table.setText(1, 1, dataSourceInfo.getTimeoutInterval().toString());
			    table.getFlexCellFormatter().addStyleName(1, 1, "valueText");
			    table.getFlexCellFormatter().setWidth(1, 1, "30%");
			    table.getFlexCellFormatter().setHorizontalAlignment(1, 1, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(1, 1, false);
			    
			    table.setText(1, 2, "Last Update:");
			    table.getFlexCellFormatter().addStyleName(1, 2, "nameText");
			    table.getFlexCellFormatter().setWidth(1, 2, "20%");
			    table.getFlexCellFormatter().setHorizontalAlignment(1, 2, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(1, 2, false);
			    table.setText(1, 3, dateFormat.format(new Date()));
			    table.getFlexCellFormatter().addStyleName(1, 3, "valueText");
			    table.getFlexCellFormatter().setWidth(1, 3, "30%");
			    table.getFlexCellFormatter().setHorizontalAlignment(1, 3, HorizontalAlignmentConstant.startOf(Direction.LTR));
			    table.getFlexCellFormatter().setWordWrap(1, 3, false);
				return table;
			}
			
		});
		
	}
}
