package com.odb.core;


/**
 * The Class PublisherInfo.
 * 
 * this class is a DTO
 */
public class PublisherInfo {
	
	/** The publisher name. */
	private String publisherID, publisherName;

	/**
	 * Gets the publisher id.
	 *
	 * @return the publisher id
	 */
	public String getPublisherID() {
		return publisherID;
	}

	/**
	 * Sets the publisher id.
	 *
	 * @param publisherID the new publisher id
	 */
	public void setPublisherID(String publisherID) {
		this.publisherID = publisherID;
	}

	/**
	 * Gets the publisher name.
	 *
	 * @return the publisher name
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * Sets the publisher name.
	 *
	 * @param publisherName the new publisher name
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

}
