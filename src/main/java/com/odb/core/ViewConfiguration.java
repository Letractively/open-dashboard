package com.odb.core;

/**
 * The Class ViewConfiguration.
 * 
 * this class is a DTO
 */
public class ViewConfiguration {
	
	/** The view location id. */
	private String viewLocationID;
	
	/** The start y. */
	private int viewHeight, viewWidth, startX, startY;
	
	/** The is displayed. */
	private boolean isDisplayed = true;
	
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
	 * Gets the view height.
	 *
	 * @return the view height
	 */
	public int getViewHeight() {
		return viewHeight;
	}
	
	/**
	 * Sets the view height.
	 *
	 * @param viewHeight the new view height
	 */
	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}
	
	/**
	 * Gets the view width.
	 *
	 * @return the view width
	 */
	public int getViewWidth() {
		return viewWidth;
	}
	
	/**
	 * Sets the view width.
	 *
	 * @param viewWidth the new view width
	 */
	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}
	
	/**
	 * Gets the start x.
	 *
	 * @return the start x
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * Sets the start x.
	 *
	 * @param startX the new start x
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}
	
	/**
	 * Gets the start y.
	 *
	 * @return the start y
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * Sets the start y.
	 *
	 * @param startY the new start y
	 */
	public void setStartY(int startY) {
		this.startY = startY;
	}
	
	/**
	 * Checks if is displayed.
	 *
	 * @return true, if is displayed
	 */
	public boolean isDisplayed() {
		return isDisplayed;
	}
	
	/**
	 * Sets the displayed.
	 *
	 * @param isDisplayed the new displayed
	 */
	public void setDisplayed(boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}
}
