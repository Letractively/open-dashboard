/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.dashboard.client.exceptions;

public class FetchDataSourceException extends Exception {
	/**
	 * Instantiates a new graph not available exception.
	 */
	public FetchDataSourceException(){
		super();
	}
	
	/**
	 * Instantiates a new graph not available exception.
	 *
	 * @param msg the msg
	 */
	public FetchDataSourceException(String msg){
		super(msg);
	}

}
