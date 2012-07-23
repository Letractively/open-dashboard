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
