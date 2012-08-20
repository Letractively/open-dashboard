package com.odb.view.dashboard.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
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
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.view.dashboard.client.charts.ChartUI;
import com.odb.view.dashboard.client.dto.DataSourceInfo;
import com.odb.view.dashboard.client.dto.PublisherInfo;

public class ClientUI {
	private static DebugWindow dbg = DebugWindow.getInstance();
	final FlowPanel flowPanel = new FlowPanel();
	private DashboardServiceAsync dashboardService = null;

	ArrayList<ChartUI> charts = new ArrayList<ChartUI>();

	public void addChart(final DataSourceConfiguration dsConfig, final int width, final int height) {
		ChartUI chartUI =new ChartUI(dashboardService, dsConfig, width, height); 
		charts.add(chartUI);
		flowPanel.add(chartUI);
	}

	public ClientUI(DashboardServiceAsync dashboardService) {
		this.dashboardService = dashboardService;
		final FlexTable splitFlexTable = new FlexTable();
		splitFlexTable.addStyleName("cw-FlexTable");
		splitFlexTable.setWidth("100%");

		// splitFlexTable.getFlexCellFormatter().setColSpan(0, 0, 2);
		splitFlexTable.setWidget(0, 0, createMenu());
		splitFlexTable.setWidget(1, 0, flowPanel);
		// splitFlexTable.getFlexCellFormatter().setHeight(1, 0, "90%");
		splitFlexTable.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		splitFlexTable.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_JUSTIFY);
		splitFlexTable.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		splitFlexTable.setWidget(2, 0, dbg.asWidget());
		RootPanel.get().add(splitFlexTable);

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
				dashboardService.getDataSourceAllDetails(newDataSourceID.toString(), new AsyncCallback<DataSourceConfiguration>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					public void onSuccess(DataSourceConfiguration result) {
						addChart(result, 400,300);
						

					}
				});

				// createChart(newDataSourceID.toString());
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

}
