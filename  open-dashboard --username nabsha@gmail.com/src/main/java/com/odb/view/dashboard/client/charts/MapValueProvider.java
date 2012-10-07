/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client.charts;

import com.odb.view.dashboard.client.TimeSeriesDataVO;
import com.sencha.gxt.core.client.ValueProvider;

public class MapValueProvider implements ValueProvider<TimeSeriesDataVO, Double> {
  private String seriesName;
 
  public MapValueProvider(String field) {
    this.seriesName = field;
  }
 
  public String getPath() {
    return seriesName;
  }
 
  public Double getValue(TimeSeriesDataVO object) {
    return object.get(seriesName);
  }
 
  public void setValue(TimeSeriesDataVO object, Double value) {
    object.put(seriesName, value);
  }
}
