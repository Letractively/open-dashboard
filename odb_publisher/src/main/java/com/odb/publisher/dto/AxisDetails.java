package com.odb.publisher.dto;

public class AxisDetails {
	private String axisName;
	private long minima;
	private long maxima;
	public String getAxisName() {
		return axisName;
	}
	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}
	public long getMinima() {
		return minima;
	}
	public void setMinima(long minima) {
		this.minima = minima;
	}
	public long getMaxima() {
		return maxima;
	}
	public void setMaxima(long maxima) {
		this.maxima = maxima;
	}
}
