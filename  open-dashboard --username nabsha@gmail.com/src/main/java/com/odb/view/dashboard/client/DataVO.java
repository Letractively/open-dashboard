/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client;

import java.io.Serializable;
import java.util.HashMap;


/**
 * The Class DataVO.
 * 
 * the general type for all chart data objects.<br>
 * any chart data object should extend this object
 * 
 * @see com.odb.view.dashboard.client.dto.LiveChartVO
 */
@SuppressWarnings("serial")
public class DataVO extends HashMap<String, Double> implements Serializable{

}
