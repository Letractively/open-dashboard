package com.odb.publisher.dto;

import java.util.ArrayList;

public class Publisher {
	private String publisherID;
	private ArrayList<DataSourceDetails> dataSources;

	public Publisher() {
		dataSources = new ArrayList<DataSourceDetails>();
	}
	public String getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(String publisherID) {
		this.publisherID = publisherID;
	}

	public ArrayList<DataSourceDetails> getDataSources() {
		return dataSources;
	}

	public void setDataSources(ArrayList<DataSourceDetails> dataSources) {
		this.dataSources = dataSources;
	}
}
