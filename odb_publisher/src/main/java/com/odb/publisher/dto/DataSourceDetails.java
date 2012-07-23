package com.odb.publisher.dto;

public class DataSourceDetails {
	private String dataSourceID;
	private DataConfiguration dataConfig;
	private DataGeneration dataGen;
	
	public DataSourceDetails() {
		dataConfig = new DataConfiguration();
		dataGen = new DataGeneration();
	}
	public String getDataSourceID() {
		return dataSourceID;
	}
	public void setDataSourceID(String dataSourceID) {
		this.dataSourceID = dataSourceID;
	}
	public DataConfiguration getDataConfig() {
		return dataConfig;
	}
	public void setDataConfig(DataConfiguration dataConfig) {
		this.dataConfig = dataConfig;
	}
	public DataGeneration getDataGen() {
		return dataGen;
	}
	public void setDataGen(DataGeneration dataGen) {
		this.dataGen = dataGen;
	}
}
