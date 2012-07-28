package com.odb.view.dashboard.client.charts;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.odb.view.dashboard.client.DataVO;
import com.odb.view.dashboard.client.dto.LiveChartVO;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.axis.TimeAxis;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

/**
 * The Class LiveChart.
 * 
 * this class is the represents the <a
 * href="http://www.sencha.com/products/extgwt">EXT GWT</a> live chart
 * implementation.
 * <p>
 * 
 * the live chart has two main axis; the X axis which is of type
 * {@link TimeAxis}, and the Y axis which is of type {@link NumericAxis}.<br>
 * 
 */
public class LiveChart extends ODBChart {

	/** The chart. */
	private Chart<LiveChartVO> chart;

	/** The store that has the displayed data. */
	private ListStore<LiveChartVO> store;

	/** The site access. */
	private LiveChartVO.LiveCharPropertyAccess siteAccess;

	/** The timeFormat. */
	private DateTimeFormat timeFormat = DateTimeFormat.getFormat("h:m:s");

	/** The time axis frequency. */
	private Long timeAxisFrequency = 1000l; // in milliseconds

	/** The num axis min. */
	private Integer numAxisMin = 0;

	/** The num axis max. */
	private Integer numAxisMax = 100;

	/** The variable name. */
	private String variableName = "Value";

	/** The threshold. */
	private int threshold;

	/** The numeric axis. */
	private NumericAxis<LiveChartVO> numericAxis;

	/** The time axis. */
	private TimeAxis<LiveChartVO> timeAxis;

	private int seriesCount;

	private ArrayList<LiveChartVO> initialDataList = null;
	/**
	 * Instantiates a new live chart. if a parameter is null, the default
	 * parameter specified.
	 * 
	 * @param timeAxisFrequency
	 *            the time axis frequency
	 * @param numAxisMin
	 *            the num axis min
	 * @param numAxisMax
	 *            the num axis max
	 * @param variableName
	 *            the variable name
	 */
	public LiveChart(Long timeAxisFrequency, Integer numAxisMin, Integer numAxisMax, String variableName, int seriesCount,ArrayList<DataVO> dataList) {
		if (timeAxisFrequency != null)
			this.timeAxisFrequency = timeAxisFrequency;
		if (numAxisMin != null)
			this.numAxisMin = numAxisMin;
		if (numAxisMax != null)
			this.numAxisMax = numAxisMax;
		if (variableName != null)
			this.variableName = variableName;
		this.seriesCount = seriesCount;
		chart = new Chart<LiveChartVO>();
		initialDataList = new ArrayList<LiveChartVO>();
		//this.dataList = (ArrayList<LiveChartVO>)dataList;
		Date dt = new Date();
		for (DataVO d : dataList) {
			initialDataList.add((LiveChartVO) d);
		}
		while(initialDataList.size() < 50) {
			initialDataList.add(new LiveChartVO(new Date(initialDataList.get(0).getDate().getTime() - timeAxisFrequency), 0, 0, 0));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobily.dashboard.client.MobilyChart#asWidget()
	 */
	@Override
	public Widget asWidget() {
		// make LiveChartVO accessible to GWT
		siteAccess = GWT.create(LiveChartVO.LiveCharPropertyAccess.class);
		chart.setChartShadow(true);
		chart.setAnimated(true);
		store = new ListStore<LiveChartVO>(siteAccess.nameKey());
//		Date date = startDate;
		// now set the init data
		for (int i = initialDataList.size()-1;i >=0; i--) {
			store.add(new LiveChartVO(initialDataList.get(i).getDate(), initialDataList.get(i).getVariable(), initialDataList.get(i).getVariable2(), initialDataList.get(i).getVariable3()));
//			date = CalendarUtil.copyDate(date);
//			date.setTime(date.getTime() + timeAxisFrequency);
		}
		chart.setStore(store);

		// init numeric numericAxis
		numericAxis = new NumericAxis<LiveChartVO>();
		numericAxis.setPosition(Position.LEFT);
		numericAxis.addField(siteAccess.variable());
		TextSprite title = new TextSprite(variableName);
		title.setFontSize(14);
		numericAxis.setTitleConfig(title);
		numericAxis.setDisplayGrid(true);
		numericAxis.setMinimum(numAxisMin);
		numericAxis.setMaximum(numAxisMax);
		numericAxis.setMinorTickSteps(10);
		chart.addAxis(numericAxis);

		// init timeAxis numericAxis
		Date endDate = new Date();
		timeAxis = new TimeAxis<LiveChartVO>();
		timeAxis.setField(siteAccess.date());
		timeAxis.setEndDate(initialDataList.get(0).getDate());
//		Date startDate = CalendarUtil.copyDate(endDate);
//		startDate.setTime(endDate.getTime() - (timeAxisFrequency * 7));
		timeAxis.setStartDate(initialDataList.get(initialDataList.size()-1).getDate());
		timeAxis.setLabelProvider(new LabelProvider<Date>() {
			public String getLabel(Date item) {
				return timeFormat.format(item);
			}
		});
		chart.addAxis(timeAxis);


		for (int i = 0; i < seriesCount; i++) {
			ValueProvider<LiveChartVO, Double> vp = null;
			java.util.Random rand = new Random();
			RGB color = new RGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));

			switch (i) {
			case 0:
				vp = siteAccess.variable();
				break;
			case 1:
				vp = siteAccess.variable2();
				break;
			case 2:
				vp = siteAccess.variable3();
				break;
			default:
				vp = siteAccess.variable();
			}
			chart.addSeries(generateSeries(vp, Position.LEFT, color));
		}
		Legend<LiveChartVO> legends = new Legend<LiveChartVO>();
		legends.setPosition(Position.BOTTOM);
		chart.setLegend(legends);
		return chart;
	}

	private LineSeries<LiveChartVO> generateSeries(ValueProvider<LiveChartVO, Double> dataSeries, Position pos, RGB color) {
		LineSeries<LiveChartVO> series = new LineSeries<LiveChartVO>();
		series.setYField(dataSeries);
		series.setYAxisPosition(pos);
		series.setStroke(color);
		series.setShowMarkers(false);
//		series.setMarkerIndex(1);
//		Sprite marker = Primitives.circle(0, 0, 0.1);
//		marker.setFill(color);

		return series;

	}

	/**
	 * Gets the chart.
	 * 
	 * @return the chart
	 */
	public Chart<LiveChartVO> getChart() {
		return chart;
	}

	/**
	 * Sets the chart.
	 * 
	 * @param chart
	 *            the new chart
	 */
	public void setChart(Chart<LiveChartVO> chart) {
		this.chart = chart;
	}

	/**
	 * Gets the time axis frequency.
	 * 
	 * @return the time axis frequency
	 */
	public Long getTimeAxisFrequency() {
		return timeAxisFrequency;
	}

	/**
	 * Sets the time axis frequency.
	 * 
	 * @param timeAxisFrequency
	 *            the new time axis frequency
	 */
	public void setTimeAxisFrequency(Long timeAxisFrequency) {
		this.timeAxisFrequency = timeAxisFrequency;
	}

	/**
	 * Gets the numeric axis.
	 * 
	 * @return the numeric axis
	 */
	public NumericAxis<LiveChartVO> getNumericAxis() {
		return numericAxis;
	}

	/**
	 * Gets the time axis.
	 * 
	 * @return the time axis
	 */
	public TimeAxis<LiveChartVO> getTimeAxis() {
		return timeAxis;
	}

	/**
	 * Gets the threshold.
	 * 
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * Sets the threshold.
	 * 
	 * @param threshold
	 *            the new threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	private static long chartUpdateCount =0;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobily.dashboard.client.MobilyChart#updateChartData(com.mobily.dashboard
	 * .client.DataVO, com.google.gwt.user.client.ui.Label)
	 */
	@Override
	public void updateChartData(DataVO data, Label errorLabel) {
		
		LiveChartVO liveChartData = (LiveChartVO) data;
		Date beginDate = CalendarUtil.copyDate(timeAxis.getStartDate());
		Date startDate = CalendarUtil.copyDate(timeAxis.getStartDate());
		Date endDate = CalendarUtil.copyDate(timeAxis.getEndDate());
		// gets the last update time
		Date newDate = liveChartData.getDate();
		// if the new data update was late for a time longer than
		// timeAxisFrequency
		if ((newDate.getTime() - endDate.getTime()) > timeAxisFrequency) {
			// threshold is decreased. and hence, an error will be displayed if
			// threshold<0
			threshold = threshold - 1;
		} else if (threshold < 0) { // but if data was not late we should
									// increase threshold if it is < 0
			threshold = threshold + 1;
		}

		if (threshold < 0) {
			// display error if threshold < 0
			errorLabel.setText("Watch out, the publisher response is late. the data may be inconsistent.");
		} else if (errorLabel.getText().contains("Watch")) { // if threshold =
																// 0, then
																// notify the
																// user that The
																// Data is
																// getting beck
																// to normal
			errorLabel.setText("The Data is getting beck to normal...");
		}
		// reset the start and end time with the new values
//		int timeDiff = (int) (newDate.getTime() - endDate.getTime());
//		initialDataList.add(liveChartData);
//		initialDataList.remove(0);
		chart.getStore().remove(0);
		//startDate.setTime(.getTime());
//		endDate.setTime(endDate.getTime() + timeDiff);
		chart.getStore().add(liveChartData);
		timeAxis.setStartDate(chart.getStore().get(0).getDate());
		timeAxis.setEndDate(chart.getStore().get(chart.getStore().size()-1).getDate());
		// now redrawChart
		chart.redrawChart();
	}

}
