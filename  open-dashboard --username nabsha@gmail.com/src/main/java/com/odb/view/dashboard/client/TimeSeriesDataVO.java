/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client;

import java.util.Date;

public class TimeSeriesDataVO extends DataVO {
	private Date key;

	public TimeSeriesDataVO() {

	}

	public TimeSeriesDataVO(Date key) {
		this.key = new Date(key.getTime());
	}

	public Date getKey() {
		return key;
	}
}
