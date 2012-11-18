/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.icepush.gwt.client.GWTPushContext;
import org.icepush.gwt.client.PushEventListener;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.odb.core.service.DataSourceConfiguration;
import com.odb.view.dashboard.client.ChartFactory;
import com.odb.view.dashboard.client.ClientCommons;
import com.odb.view.dashboard.client.DashboardServiceAsync;
import com.odb.view.dashboard.client.DataVO;
import com.odb.view.dashboard.client.DebugWindow;
import com.odb.view.dashboard.client.TimeSeriesDataVO;
import com.odb.view.dashboard.client.exceptions.ChartSettingsNotValidException;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ChartUI implements IsWidget {
	private DashboardServiceAsync dashboardService = null;
	private static DebugWindow dbg = DebugWindow.getInstance();
	private DataSourceConfiguration dsConfig = null;
	private ODBChart chart = null;
	final int width, height;
	final Label errorLabel = new Label();
	final FlexTable flexTable;
	GWTPushContext pushContext = GWTPushContext.getInstance();
	PushEventListener pushListener = null;
	public void disableLive() {
		pushContext.removePushEventListener(pushListener);
		pushListener = null;
		
	}
	
	public void enableLive() {
		pushContext.addPushEventListener(pushListener = new PushEventListener() {
			public void onPushEvent() {
				dashboardService.getDataUpdate(dsConfig.getDsID(), "1", dsConfig.getSeriesCount(), 1, new AsyncCallback<ArrayList<DataVO>>() {
					public void onFailure(Throwable caught) {
						errorLabel.setText("Error, could not get Data Update for Data Source: " + dsConfig.getDsName()
								+ ". Please Contact System Support. ");
						dbg.debug("Failed in getDataUpdate " + caught);
					}

					public void onSuccess(ArrayList<DataVO> result) {
						chart.updateChartData(result.get(0), errorLabel);
						flexTable.setText(1, 3, ClientCommons.dateFormat.format(new Date()));
//						debug(dsConfig.getDsID() + " # " + ((com.odb.view.dashboard.client.dto.LiveChartVO) result.get(0)).getDate() + ":"
//								+ ((com.odb.view.dashboard.client.dto.LiveChartVO) result.get(0)).getVariable() + " | "
//								+ ((com.odb.view.dashboard.client.dto.LiveChartVO) result.get(0)).getVariable2() + " | "
//								+ ((com.odb.view.dashboard.client.dto.LiveChartVO) result.get(0)).getVariable3() + " | ");
					}
				});
			}
		}, dsConfig.getDsID());
		
	}

	public ChartUI(DashboardServiceAsync dashboardService, DataSourceConfiguration dsConfig, int width, int height) {
		this.dashboardService = dashboardService;
		this.dsConfig = dsConfig;
		this.width = width;
		this.height = height;
		flexTable = createInfoTable(dsConfig.getPublisherID(), dsConfig.getDsName(), dsConfig.getDsTimeoutInterval());
	}
	private Widget createChart(final DataSourceConfiguration dsConfig, final int width, final int height) {
		final ContentPanel panel = new FramedPanel();
		dashboardService.getDataUpdate(dsConfig.getDsID(), "1", dsConfig.getSeriesCount(), 50, new AsyncCallback<ArrayList<DataVO>>() {

			public void onFailure(Throwable caught) {
				dbg.debug("Failed in getDataUpdate " + caught);
			}

			public void onSuccess(ArrayList<DataVO> result) {
				// the panel that will hold the chart

				//panel.setCollapsible(true);
				dbg.debug("seriesSetCount=" + result.size());

				new Draggable(panel, panel.getHeader()).setUseProxy(false);
				final Resizable resize = new Resizable(panel, Dir.E, Dir.SE, Dir.S);
				resize.setMinHeight(400);
				resize.setMinWidth(400);
				panel.setLayoutData(HasHorizontalAlignment.ALIGN_LEFT);
				panel.getElement().getStyle().setMargin(2, Unit.PX);
				// set a header of the panel
				panel.setHeadingText(dsConfig.getDsName());
				panel.setPixelSize(width, height);
				panel.setBodyBorder(true);
				panel.setBodyStyleName("white-bg");

				try {
					chart = ChartFactory.getChart(dsConfig,result,ChartType.VERTICAL_BAR);

					final ToggleButton toggleLive = new ToggleButton("Live");
					toggleLive.setValue(true,false);
//					toggleLive.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//						
//						public void onValueChange(ValueChangeEvent<Boolean> event) {
//							if (event.getValue()) {
//								enableLive();
//							} else {
//								disableLive();
//							}
//							
//						}
//					});
					toggleLive.addSelectHandler(new SelectHandler() {
						public void onSelect(SelectEvent event) {							
							if (pushListener == null){
								enableLive();
								toggleLive.setValue(true, false, true);
							} else {
								disableLive();
								toggleLive.setValue(false, false, true);
							}
						}
					});

//					final ComboBox<LineSeries<TimeSeriesDataVO>> box = new ComboBox<LineSeries<TimeSeriesDataVO>>(fieldStore,
//							new LabelProvider<LineSeries<TimeSeriesDataVO>>() {
//								public String getLabel(LineSeries<TimeSeriesDataVO> item) {
//									return item.getYField().getPath();
//								}
//							});

					final TextField fieldInput = new TextField();
					fieldInput.setValue("first");
					fieldInput.setAllowBlank(false);
					fieldInput.addValidator(new MaxLengthValidator(20));
					final RegExp regex = RegExp.compile("\\s");
					fieldInput.addValidator(new Validator<String>() {
						public List<EditorError> validate(Editor<String> editor, String value) {
							if (regex.test(value)) {
								List<EditorError> errors = new ArrayList<EditorError>();
								errors.add(new DefaultEditorError(editor, "Field name cannot contain spaces.", ""));
								return errors;
							}
							return null;
						}
					});

					TextButton add = new TextButton("Add");
					add.addSelectHandler(new SelectHandler() {
						public void onSelect(SelectEvent event) {
//							String field = fieldInput.getValue();
//							if (fieldInput.isCurrentValid() && field.length() > 0 && fieldStore.findModelWithKey(field) == null && fieldStore.size() < 10) {
//								for (int i = 0; i < store.size(); i++) {
//									TimeSeriesDataVO item = store.get(i);
//									item.put(field, Math.random() * 100);
//								}
//								LineSeries<TimeSeriesDataVO> series = createLine(field);
//								fieldStore.add(series);
//								axis.addField(series.getYField());
//								chart.addSeries(series);
//								chart.redrawChart();
//							}
						}
					});

					TextButton remove = new TextButton("Remove");
					remove.addSelectHandler(new SelectHandler() {
						public void onSelect(SelectEvent event) {
//							String field = box.getText();
//							LineSeries<TimeSeriesDataVO> series = fieldStore.findModelWithKey(field);
//							if (field.length() > 0 && series != null && fieldStore.size() > 0) {
//								for (int i = 0; i < store.size(); i++) {
//									TimeSeriesDataVO item = store.get(i);
//									item.remove(field);
//								}
//								fieldStore.remove(series);
//								axis.removeField(series.getYField());
//								chart.removeSeries(series);
//								chart.redrawChart();
//							}
//							box.clear();
						}
					});

					ToolBar toolBar = new ToolBar();
					toolBar.add(toggleLive);
					toolBar.add(new SeparatorToolItem());
					toolBar.add(add);
					toolBar.add(fieldInput);
					toolBar.add(new SeparatorToolItem());
					toolBar.add(remove);
//					toolBar.add(box);

					VerticalLayoutContainer layout2 = new VerticalLayoutContainer();

					toolBar.setLayoutData(new VerticalLayoutData(1, -1));
					layout2.add(toolBar);

//					chart.setLayoutData(new VerticalLayoutData(1, 1));
					layout2.add(chart.asWidget());
					final VerticalLayoutContainer layout = new VerticalLayoutContainer();

					layout.setBorders(true);
					// table that provide the data source information
					layout.add(flexTable, new VerticalLayoutData(1, height / 10));
					errorLabel.setHeight("20px");
					errorLabel.setText("");

					layout.add(layout2,new VerticalLayoutData(1, 1));
					layout.add(errorLabel);
					enableLive();
					panel.add(layout);
					

				} catch (ChartSettingsNotValidException e) {
					errorLabel.setText(e.getMessage());
					dbg.debug("Failed in createChart " + e);
				}
			}
		});
		return panel;
	}
	
	private FlexTable createInfoTable(String publisherID, String dsName, Long timeoutInterval) {
		FlexTable table = new FlexTable();
		table.setText(0, 0, "Publisher ID:");
		table.getFlexCellFormatter().addStyleName(0, 0, "nameText");
		table.getFlexCellFormatter().setWidth(0, 0, "20%");
		table.getFlexCellFormatter().setHorizontalAlignment(0, 0, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(0, 0, false);
		table.setText(0, 1, publisherID);
		table.getFlexCellFormatter().addStyleName(0, 1, "valueText");
		table.getFlexCellFormatter().setWidth(0, 1, "30%");
		table.getFlexCellFormatter().setHorizontalAlignment(0, 1, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(0, 1, false);

		table.setText(0, 2, "Data Source Name:");
		table.getFlexCellFormatter().addStyleName(0, 2, "nameText");
		table.getFlexCellFormatter().setWidth(0, 2, "20%");
		table.getFlexCellFormatter().setHorizontalAlignment(0, 2, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(0, 2, false);
		table.setText(0, 3, dsName);
		table.getFlexCellFormatter().addStyleName(0, 3, "valueText");
		table.getFlexCellFormatter().setWidth(0, 3, "30%");
		table.getFlexCellFormatter().setHorizontalAlignment(0, 3, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(0, 3, false);

		table.setText(1, 0, "Timeout Interval (milis):");
		table.getFlexCellFormatter().addStyleName(1, 0, "nameText");
		table.getFlexCellFormatter().setWidth(1, 0, "20%");
		table.getFlexCellFormatter().setHorizontalAlignment(1, 0, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(1, 0, false);
		table.setText(1, 1, timeoutInterval.toString());
		table.getFlexCellFormatter().addStyleName(1, 1, "valueText");
		table.getFlexCellFormatter().setWidth(1, 1, "30%");
		table.getFlexCellFormatter().setHorizontalAlignment(1, 1, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(1, 1, false);

		table.setText(1, 2, "Last Update:");
		table.getFlexCellFormatter().addStyleName(1, 2, "nameText");
		table.getFlexCellFormatter().setWidth(1, 2, "20%");
		table.getFlexCellFormatter().setHorizontalAlignment(1, 2, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(1, 2, false);
		table.setText(1, 3, ClientCommons.dateFormat.format(new Date()));
		table.getFlexCellFormatter().addStyleName(1, 3, "valueText");
		table.getFlexCellFormatter().setWidth(1, 3, "30%");
		table.getFlexCellFormatter().setHorizontalAlignment(1, 3, HorizontalAlignmentConstant.startOf(Direction.LTR));
		table.getFlexCellFormatter().setWordWrap(1, 3, false);
		return table;
	}
	public Widget asWidget() {		
		return createChart(dsConfig, width, height);
	}


}
