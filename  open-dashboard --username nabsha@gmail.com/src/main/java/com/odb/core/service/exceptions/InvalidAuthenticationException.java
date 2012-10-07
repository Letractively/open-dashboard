/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.core.service.exceptions;

public class InvalidAuthenticationException extends Exception {
	public InvalidAuthenticationException() {
		super();
	}
	
	public InvalidAuthenticationException(String msg) {
		super(msg);
	}
}
