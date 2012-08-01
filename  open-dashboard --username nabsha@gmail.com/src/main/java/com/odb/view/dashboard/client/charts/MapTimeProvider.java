package com.odb.view.dashboard.client.charts;

import java.util.Date;

import com.odb.view.dashboard.client.TimeSeriesDataVO;
import com.sencha.gxt.core.client.ValueProvider;

public class MapTimeProvider implements ValueProvider<TimeSeriesDataVO, Date> {

	public MapTimeProvider() {
		// TODO Auto-generated constructor stub
	}


	public String getPath() {
		return null;
	}

	public Date getValue(TimeSeriesDataVO object) {
		return object.getKey();
	}

	public void setValue(TimeSeriesDataVO object, Date value) {
		// TODO Auto-generated method stub

	}
}