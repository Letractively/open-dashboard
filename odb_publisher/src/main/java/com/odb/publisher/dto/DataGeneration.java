package com.odb.publisher.dto;

import java.util.ArrayList;

public class DataGeneration {
	private int jobFrequency;
	private ArrayList<SeriesJob> seriesJobs;

	public DataGeneration() {
		seriesJobs = new ArrayList<SeriesJob>();
	}
	public int getJobFrequency() {
		return jobFrequency;
	}
	public void setJobFrequency(int jobFrequency) {
		this.jobFrequency = jobFrequency;
	}
	public ArrayList<SeriesJob> getSeriesJob() {
		return seriesJobs;
	}

	public void setSeriesJob(ArrayList<SeriesJob> seriesJob) {
		this.seriesJobs = seriesJob;
	}
}
