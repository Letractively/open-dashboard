/**
 * 
 */
package com.odb.view.dashboard.client.exceptions;


/**
 * The Class ViewSettingNotConsistentException.
 *
 */
@SuppressWarnings("serial")
public class ViewSettingNotConsistentException extends Exception {
	
	/**
	 * Instantiates a new view setting not consistent exception.
	 */
	public ViewSettingNotConsistentException(){
		super();
	}
	
	/**
	 * Instantiates a new view setting not consistent exception.
	 *
	 * @param msg the msg
	 */
	public ViewSettingNotConsistentException(String msg){
		super(msg);
	}

}
