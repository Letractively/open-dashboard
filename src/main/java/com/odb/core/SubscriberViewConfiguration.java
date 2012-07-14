package com.odb.core;

/**
 * The Class SubscriberViewConfiguration.
 * 
 * this class is a DTO
 */
public class SubscriberViewConfiguration {
	
	/** The subsriber data source id. */
	private String subscriberID, viewLocationID, subsriberDataSourceID;

	/**
	 * Instantiates a new subscriber view configuration.
	 */
	public SubscriberViewConfiguration(){
		
	}
	
	/**
	 * Instantiates a new subscriber view configuration.
	 *
	 * @param subscriberID the subscriber id
	 * @param viewLocationID the view location id
	 * @param subsriberDataSourceID the subsriber data source id
	 */
	public SubscriberViewConfiguration(String subscriberID, String viewLocationID, String subsriberDataSourceID){
		
	}
	
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
	 * Gets the view location id.
	 *
	 * @return the view location id
	 */
	public String getViewLocationID() {
		return viewLocationID;
	}

	/**
	 * Sets the view location id.
	 *
	 * @param viewLocationID the new view location id
	 */
	public void setViewLocationID(String viewLocationID) {
		this.viewLocationID = viewLocationID;
	}

	/**
	 * Gets the subsriber data source id.
	 *
	 * @return the subsriber data source id
	 */
	public String getSubsriberDataSourceID() {
		return subsriberDataSourceID;
	}

	/**
	 * Sets the subsriber data source id.
	 *
	 * @param subsriberDataSourceID the new subsriber data source id
	 */
	public void setSubsriberDataSourceID(String subsriberDataSourceID) {
		this.subsriberDataSourceID = subsriberDataSourceID;
	}
	
}
