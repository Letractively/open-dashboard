package com.odb.view.dashboard.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.icepush.gwt.client.GWTPushContext;
import org.icepush.gwt.client.PushEventListener;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.odb.view.core.ActionProcessor;
import com.odb.view.dashboard.client.charts.ODBChart;
import com.odb.view.dashboard.client.dto.DataSourceAxisInfo;
import com.odb.view.dashboard.client.dto.DataSourceInfo;
import com.odb.view.dashboard.client.dto.PublisherInfo;
import com.odb.view.dashboard.client.dto.SubscriberDataSource;
import com.odb.view.dashboard.client.dto.ViewConfig;
import com.odb.view.dashboard.client.dto.ViewSettings;
import com.odb.view.dashboard.client.exceptions.ChartSettingsNotValidException;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.fx.client.Resizable;
import com.sencha.gxt.theme.base.client.panel.FramedPanelAppearance;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

/**
 * Dashboard class is the GWT Entry point classes that define
 * <code>onModuleLoad()</code>. <br>
 * The class uses the {@link DashboardService#getCurrentViewSettings()} to load
 * the user preferences on load time and once the settings is loaded, it begins
 * to draw the {@link ODBChart} for each graph
 * <p>
 * The class registers a {@link PushEventListener} listener to listen to the
 * events fired by the
 * {@link ActionProcessor#firePushEventAction(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
 * . <br>
 * Once the event is fired, the
 * {@link DashboardService#getDataUpdate(String, String)} get called. and the
 * chart the updated with the {@link ODBChart#updateChartData(DataVO, Label)}
 * <p>
 * Note that this class is a part of the client package. And hence, it will be
 * converted to Javascript code in the "GWT compilation" process
 * <p>
 * 
 * see <a href="http://code.google.com/webtoolkit/doc/latest/tutorial/">GWT
 * Tutorial</a> And <a
 * href="http://wiki.icesoft.org/display/PUSH/GWT+Integration">ICEpush GWT
 * Integration</a>
 * 
 * 
 */
public class Dashboard implements EntryPoint {

	/** The dashboard RPC service. */
	private DashboardServiceAsync dashboardService = GWT.create(DashboardService.class);

	/** The date format. */
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy h:m:s a");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	private static final TextArea debugLog = new TextArea();

	private void debug(String msg) {
		debugLog.setText(msg + "\n" + debugLog.getText());
	}

	private Widget setupDebug() {
		//debugLog.setCharacterWidth(20);
		final ContentPanel panel = new ContentPanel(GWT.<ContentPanelAppearance> create(FramedPanelAppearance.class));
		panel.setCollapsible(true);
		new Resizable(panel);

		panel.getElement().getStyle().setMargin(2, Unit.PX);
		// set a header of the panel
		panel.setHeadingText("Debug Messages");
		panel.setBodyBorder(true);
		panel.setBodyStyleName("white-bg");
		debugLog.setVisibleLines(10);
		debugLog.setWidth("100%");
		debugLog.setHeight("10%");
//		debugLog.setVisibleLines(40);
		debugLog.setReadOnly(true);
		ScrollPanel debugScrollable = new ScrollPanel(debugLog);
		panel.add(debugScrollable);
		return debugScrollable;
	}

	// Create a command that will execute on menu item selection
	Command menuCommand = new Command() {

		public void execute() {
			Window.alert("Wake up...");

		}
	};

	private DialogBox createDialogBox(ArrayList<PublisherInfo> pubInfoList) {
		// Create a dialog box and set the caption text
		final DialogBox dialogBox = new DialogBox();
		final StringBuffer newDataSourceID = new StringBuffer();
		dialogBox.ensureDebugId("cwDialogBox");
		dialogBox.setText("Select DataSource");

		// Create a table to layout the content
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(4);
		dialogBox.setWidget(dialogContents);

		final Tree dynamicTree = createDynamicTree(pubInfoList);
		dynamicTree.ensureDebugId("cwTree-dynamicTree");
		ScrollPanel dynamicTreeWrapper = new ScrollPanel(dynamicTree);
		dynamicTreeWrapper.ensureDebugId("cwTree-dynamicTree-Wrapper");
		dynamicTreeWrapper.setSize("300px", "300px");

		// Wrap the dynamic tree in a DecoratorPanel
		DecoratorPanel dynamicDecorator = new DecoratorPanel();
		dynamicDecorator.setWidget(dynamicTreeWrapper);
		dialogContents.add(dynamicDecorator);

		// Add a close button at the bottom of the dialog
		Button nextButton = new Button("Next", new ClickHandler() {
			public void onClick(ClickEvent event) {
				newDataSourceID.append(dynamicTree.getSelectedItem().getText());
//				dashboardService.getDataSourceAllDetails(dataSourceID, callback)
				dashboardService.getDataSourceAllDetails(newDataSourceID.toString(), new AsyncCallback<HashMap<String, Serializable>>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					public void onSuccess(HashMap<String, Serializable> result) {
						flowPanel.add(createChart((DataSourceInfo)result.get("dataSourceInfo"), (ArrayList<DataSourceAxisInfo>)result.get("dataSourceAxisInfoList"), "1", 400, 300));
						
					}
				});
					
				
				//createChart(newDataSourceID.toString());
				dialogBox.hide();
			}
		});
		dialogContents.add(nextButton);
		if (LocaleInfo.getCurrentLocale().isRTL()) {
			dialogContents.setCellHorizontalAlignment(nextButton, HasHorizontalAlignment.ALIGN_LEFT);

		} else {
			dialogContents.setCellHorizontalAlignment(nextButton, HasHorizontalAlignment.ALIGN_RIGHT);
		}

		// Add a close button at the bottom of the dialog
		Button closeButton = new Button("Close", new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		dialogContents.add(closeButton);
		if (LocaleInfo.getCurrentLocale().isRTL()) {
			dialogContents.setCellHorizontalAlignment(closeButton, HasHorizontalAlignment.ALIGN_LEFT);

		} else {
			dialogContents.setCellHorizontalAlignment(closeButton, HasHorizontalAlignment.ALIGN_RIGHT);
		}

		// Return the dialog box
		return dialogBox;
	}

	/**
	 * Create a dynamic tree that will add a random number of children to each
	 * node as it is clicked.
	 * 
	 * @return the new tree
	 */
	private Tree createDynamicTree(ArrayList<PublisherInfo> pubInfoList) {
		// Create a new tree
		final Tree dynamicTree = new Tree();

		for (int i = 0; i < pubInfoList.size(); i++) {
			TreeItem item = dynamicTree.addItem(pubInfoList.get(i).getPublisherID());
			item.addItem("");
		}

		dynamicTree.addOpenHandler(new OpenHandler<TreeItem>() {
			public void onOpen(OpenEvent<TreeItem> event) {
				final TreeItem item = event.getTarget();
				dashboardService.getDataSources(event.getTarget().getText(), new AsyncCallback<ArrayList<DataSourceInfo>>() {

					public void onSuccess(ArrayList<DataSourceInfo> result) {
						item.removeItems();
						for (DataSourceInfo dsInfo : result) {
							TreeItem child = item.addItem(dsInfo.getDataSourceID());
						}
					}

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
			}
		});

		return dynamicTree;
	}

	Command addCommand = new Command() {

		public void execute() {
			dashboardService.getPublisherInfo(new AsyncCallback<ArrayList<PublisherInfo>>() {

				public void onSuccess(ArrayList<PublisherInfo> result) {
					DialogBox msg = createDialogBox(result);
					msg.center();
					msg.show();

				}

				public void onFailure(Throwable caught) {

				}
			});

		}
	};

	private Widget createMenu() {

		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);

		MenuBar fileMenu = new MenuBar();
		menu.addItem(new MenuItem("File", fileMenu));
		fileMenu.addItem("Add Chart", addCommand);
		fileMenu.addItem("Del Chart", menuCommand);

		MenuBar helpMenu = new MenuBar();
		menu.addItem(new MenuItem("Help", helpMenu));
		helpMenu.addItem("Help", menuCommand);
		helpMenu.addItem("About", menuCommand);

		return menu;
	}

	private Widget createChart(final DataSourceInfo dataSourceInfo, ArrayList<DataSourceAxisInfo> dataSourceAxisInfoList, String chartID,
			int width, int height) {
		// the panel that will hold the chart
		final ContentPanel panel = new ContentPanel(GWT.<ContentPanelAppearance> create(FramedPanelAppearance.class));
		panel.setCollapsible(true);
		new Draggable(panel);
		new Resizable(panel);
		panel.setLayoutData(HasHorizontalAlignment.ALIGN_LEFT);
		panel.getElement().getStyle().setMargin(2, Unit.PX);
		// set a header of the panel
		panel.setHeadingText("Chart " + chartID);
		panel.setPixelSize(width, height);
		panel.setBodyBorder(true);
		panel.setBodyStyleName("white-bg");

		final VerticalLayoutContainer layout = new VerticalLayoutContainer();

		layout.setBorders(true);
		// table that provide the data source information
		final FlexTable flexTable = createInfoTable(dataSourceInfo);
		layout.add(flexTable, new VerticalLayoutData(1, height / 10));
		final Label errorLabel = new Label();
		errorLabel.setHeight("20px");
		errorLabel.setText("");

		try {
			final ODBChart chart = ChartFactory.getChart(dataSourceInfo, dataSourceAxisInfoList);
			layout.add(chart.asWidget(), new VerticalLayoutData(1, 1));
			layout.add(errorLabel);

			panel.add(layout);
			GWTPushContext pushContext = GWTPushContext.getInstance();
			pushContext.addPushEventListener(new PushEventListener() {
				public void onPushEvent() {
					dashboardService.getDataUpdate(dataSourceInfo.getDataSourceID(), "1", new AsyncCallback<DataVO>() {
						public void onFailure(Throwable caught) {
							errorLabel.setText("Error, could not get Data Update for Data Source: " + dataSourceInfo.getDataSourceName()
									+ ". Please Contact System Support. ");
						}

						public void onSuccess(DataVO result) {
							chart.updateChartData(result, errorLabel);
							flexTable.setText(1, 3, dateFormat.format(new Date()));
							debug(dataSourceInfo.getDataSourceID() + " # " +
									((com.odb.view.dashboard.client.dto.LiveChartVO)result).getDate() + ":" 
							+ ((com.odb.view.dashboard.client.dto.LiveChartVO)result).getVariable() + " | "
							+ ((com.odb.view.dashboard.client.dto.LiveChartVO)result).getVariable2() + " | "
							+ ((com.odb.view.dashboard.client.dto.LiveChartVO)result).getVariable3() + " | ");
						}
					});
				}
			}, dataSourceInfo.getDataSourceID());

		} catch (ChartSettingsNotValidException e) {
			errorLabel.setText(e.getMessage());
		}
		return panel;
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

	final FlowPanel flowPanel = new FlowPanel();
	public void onModuleLoad() {
		
		final FlexTable splitFlexTable = new FlexTable();
		splitFlexTable.addStyleName("cw-FlexTable");
		splitFlexTable.setWidth("100%");


		//splitFlexTable.getFlexCellFormatter().setColSpan(0, 0, 2);
		splitFlexTable.setWidget(0, 0, createMenu());
		splitFlexTable.setWidget(1, 0, flowPanel);
//		splitFlexTable.getFlexCellFormatter().setHeight(1, 0, "90%");
		splitFlexTable.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		splitFlexTable.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_JUSTIFY);
		splitFlexTable.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		splitFlexTable.setWidget(2, 0, setupDebug());
		RootPanel.get().add(splitFlexTable);

		dashboardService.getCurrentViewSettings(new AsyncCallback<ViewSettings>() {

			public void onFailure(Throwable caught) {
				debug(caught.getMessage() + ", Please Consult System Support.");
			}

			public void onSuccess(ViewSettings viewSettings) {
				debug("Success");
				for (ViewConfig viewConfig : viewSettings.viewConfigList) {
					final DataSourceInfo dataSourceInfo = (DataSourceInfo) viewSettings.viewConfigMap.get("dataSourceInfo_" + viewConfig.getViewLocationID());
					final SubscriberDataSource subscriberDataSource = (SubscriberDataSource) viewSettings.viewConfigMap.get("subscriberDataSource_"
							+ viewConfig.getViewLocationID());

					flowPanel.add(createChart((DataSourceInfo) viewSettings.viewConfigMap.get("dataSourceInfo_" + viewConfig.getViewLocationID()),
							((ArrayList<DataSourceAxisInfo>) viewSettings.viewConfigMap.get("dataSourceAxisInfoList_" + viewConfig.getViewLocationID())),
							viewConfig.getViewLocationID(), viewConfig.getViewWidth(), viewConfig.getViewHeight()));

				}

			}

		});

	}
}
