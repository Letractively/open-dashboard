/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
/**
 * 
 */
package com.odb.view.dashboard.client.exceptions;


/**
 * The Class GraphNotAvailableException.
 *
 */
@SuppressWarnings("serial")
public class GraphNotAvailableException extends Exception {
	
	/**
	 * Instantiates a new graph not available exception.
	 */
	public GraphNotAvailableException(){
		super();
	}
	
	/**
	 * Instantiates a new graph not available exception.
	 *
	 * @param msg the msg
	 */
	public GraphNotAvailableException(String msg){
		super(msg);
	}

}
