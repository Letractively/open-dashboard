/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.core.dao.dto;

/**
 * The Class SubscriberInfo.
 * 
 * this class is a DTO
 */
public class SubscriberInfo {
	
	/** The subscriber password. */
	private String subscriberID, subscriberName, subscriberPassword;

	/**
	 * Gets the subscriber id.
	 *
	 * @return the subscriber id
	 */
	public String getSubscriberID() {
		return subscriberID;
	}

	/**
	 * Sets the subscriber id.
	 *
	 * @param subscriberID the new subscriber id
	 */
	public void setSubscriberID(String subscriberID) {
		this.subscriberID = subscriberID;
	}

	/**
	 * Gets the subscriber name.
	 *
	 * @return the subscriber name
	 */
	public String getSubscriberName() {
		return subscriberName;
	}

	/**
	 * Sets the subscriber name.
	 *
	 * @param subscriberName the new subscriber name
	 */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	/**
	 * Gets the subscriber password.
	 *
	 * @return the subscriber password
	 */
	public String getSubscriberPassword() {
		return subscriberPassword;
	}

	/**
	 * Sets the subscriber password.
	 *
	 * @param subscriberPassword the new subscriber password
	 */
	public void setSubscriberPassword(String subscriberPassword) {
		this.subscriberPassword = subscriberPassword;
	}	
}
