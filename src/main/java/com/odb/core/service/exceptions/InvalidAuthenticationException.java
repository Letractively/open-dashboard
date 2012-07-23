package com.odb.core.service.exceptions;

public class InvalidAuthenticationException extends Exception {
	public InvalidAuthenticationException() {
		super();
	}
	
	public InvalidAuthenticationException(String msg) {
		super(msg);
	}
}
