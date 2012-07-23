package com.odb.publisher.dto;

import java.util.HashMap;

public class SeriesJob {
	private String jobSourceType;
	private String job;
	private HashMap<String, String> jobAttributes;
	
	public SeriesJob() {
		jobAttributes = new HashMap<String, String>();
	}
	public String getJobSourceType() {
		return jobSourceType;
	}
	public void setJobSourceType(String jobSourceType) {
		this.jobSourceType = jobSourceType;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public HashMap<String, String> getJobAttributes() {
		return jobAttributes;
	}
	public void setJobAttributes(HashMap<String, String> jobAttributes) {
		this.jobAttributes = jobAttributes;
	}
	
}
