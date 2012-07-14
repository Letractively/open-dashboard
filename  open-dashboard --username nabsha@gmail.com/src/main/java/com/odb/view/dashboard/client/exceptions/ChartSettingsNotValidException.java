/**
 * 
 */
package com.odb.view.dashboard.client.exceptions;


/**
 * The Class ChartSettingsNotValidException.
 *
 */
@SuppressWarnings("serial")
public class ChartSettingsNotValidException extends Exception {
	
	/**
	 * Instantiates a new chart settings not valid exception.
	 */
	public ChartSettingsNotValidException(){
		super();
	}
	
	/**
	 * Instantiates a new chart settings not valid exception.
	 *
	 * @param msg the msg
	 */
	public ChartSettingsNotValidException(String msg){
		super(msg);
	}

}
