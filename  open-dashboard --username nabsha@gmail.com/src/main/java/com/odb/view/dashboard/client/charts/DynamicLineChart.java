package com.odb.view.dashboard.client.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.icepush.gwt.client.GWTPushContext;
import org.icepush.gwt.client.PushEventListener;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.odb.view.dashboard.client.DataVO;
import com.odb.view.dashboard.client.DebugWindow;
import com.odb.view.dashboard.client.TimeSeriesDataVO;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.axis.TimeAxis;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.chart.series.SeriesHighlighter;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.DrawFx;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
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

public class DynamicLineChart extends ODBChart {

	private DebugWindow dbg = DebugWindow.getInstance();
	private final NumericAxis<TimeSeriesDataVO> axis = new NumericAxis<TimeSeriesDataVO>();
	private final TimeAxis<TimeSeriesDataVO> timeAxis = new TimeAxis<TimeSeriesDataVO>();
	final Chart<TimeSeriesDataVO> chart = new Chart<TimeSeriesDataVO>();
	final ListStore<TimeSeriesDataVO> store = new ListStore<TimeSeriesDataVO>(new ModelKeyProvider<TimeSeriesDataVO>() {
		public String getKey(TimeSeriesDataVO item) {
			return String.valueOf(item.getKey());
		}
	});

	public DynamicLineChart() {

	}

	public DynamicLineChart(int seriesCount, Integer min, Integer max, ArrayList<TimeSeriesDataVO> dataList) {
		for (TimeSeriesDataVO data : dataList) {
			store.add(0,data);
		}

	}
	
	public void redraw() {
		chart.redrawChart();
	}
	
	public Widget asWidget() {
		final ListStore<LineSeries<TimeSeriesDataVO>> fieldStore = new ListStore<LineSeries<TimeSeriesDataVO>>(
				new ModelKeyProvider<LineSeries<TimeSeriesDataVO>>() {
					public String getKey(LineSeries<TimeSeriesDataVO> item) {
						return item.getYField().getPath();
					}
				});

		chart.setStore(store);
		chart.setShadowChart(true);
		chart.setAnimated(true);

		Set<String> keys = store.get(0).keySet();
		for (String k : keys) {
			dbg.debug("Key=" + k);
			LineSeries<TimeSeriesDataVO> series = createLine(k);
			axis.addField(series.getYField());
			chart.addSeries(series);
			fieldStore.add(series);
		}

		axis.setPosition(Position.LEFT);
		TextSprite title = new TextSprite("Number of Hits");
		title.setFontSize(18);
		axis.setTitleConfig(title);
		axis.setMinorTickSteps(1);
		axis.setDisplayGrid(true);
		PathSprite odd = new PathSprite();
		odd.setOpacity(1);
		odd.setFill(new Color("#ddd"));
		odd.setStroke(new Color("#bbb"));
		odd.setStrokeWidth(0.5);
		axis.setGridOddConfig(odd);
		chart.addAxis(axis);

		timeAxis.setField(new MapTimeProvider());
		dbg.debug("StartDate=" + store.get(0).getKey());
		timeAxis.setStartDate(store.get(0).getKey());
		dbg.debug("EndDate=" + store.get(store.size() - 1).getKey());
		timeAxis.setEndDate(store.get(store.size() - 1).getKey());
		timeAxis.setPosition(Position.BOTTOM);
		timeAxis.setLabelProvider(new LabelProvider<Date>() {
			public String getLabel(Date item) {
				return DateTimeFormat.getFormat("h:m:s").format(item);
			}
		});
		chart.addAxis(timeAxis);

		Legend<TimeSeriesDataVO> legend = new Legend<TimeSeriesDataVO>();
		legend.setPosition(Position.RIGHT);
		legend.setItemHighlighting(true);
		legend.setItemHiding(true);
		chart.setLegend(legend);

		chart.setLayoutData(new VerticalLayoutData(1, 1));
		return chart;
	}

	private LineSeries<TimeSeriesDataVO> createLine(String seriesName) {
		MapValueProvider valueProvider = new MapValueProvider(seriesName);
		LineSeries<TimeSeriesDataVO> series = new LineSeries<TimeSeriesDataVO>();
		series.setYAxisPosition(Position.LEFT);
		Color color = new RGB((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
		series.setStroke(color);
		series.setStrokeWidth(3);
		series.setShowMarkers(true);
		Sprite marker = Primitives.circle(0, 0, 6);
		marker.setFill(color);
		series.setMarkerConfig(marker);
		series.setYField(valueProvider);
		series.setLineHighlighter(new SeriesHighlighter() {

			public void highlight(Sprite sprite) {
				DrawFx.createStrokeWidthAnimator(sprite, 6).run(250);
			}

			public void unHighlight(Sprite sprite) {
				DrawFx.createStrokeWidthAnimator(sprite, 3).run(250);
			}
		});

		return series;
	}

	@Override
	public void updateChartData(DataVO data, Label errorLabel) {
		TimeSeriesDataVO ts = (TimeSeriesDataVO)data;
		chart.getStore().remove(0);
		chart.getStore().add(ts);
		timeAxis.setStartDate(chart.getStore().get(0).getKey());
		timeAxis.setEndDate(chart.getStore().get(chart.getStore().size()-1).getKey());
		chart.redrawChart();

	}
}