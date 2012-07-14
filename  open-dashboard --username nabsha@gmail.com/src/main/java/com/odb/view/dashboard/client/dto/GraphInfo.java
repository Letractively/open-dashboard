package com.odb.view.dashboard.client.dto;

import java.io.Serializable;


/**
 * The Class GraphInfo.
 */
@SuppressWarnings("serial")
public class GraphInfo implements Serializable{
	
	/** The graph type. */
	private String graphID, graphName, graphType;
	
	/**
	 * Instantiates a new graph info.
	 */
	public GraphInfo(){}
	
	/**
	 * Instantiates a new graph info.
	 *
	 * @param graphID the graph id
	 * @param graphName the graph name
	 * @param graphType the graph type
	 */
	public GraphInfo(String graphID, String graphName, String graphType){
		this.graphID=graphID;
		this.graphName=graphName;
		this.graphType=graphType;
	}

	/**
	 * Gets the graph id.
	 *
	 * @return the graph id
	 */
	public String getGraphID() {
		return graphID;
	}

	/**
	 * Sets the graph id.
	 *
	 * @param graphID the new graph id
	 */
	public void setGraphID(String graphID) {
		this.graphID = graphID;
	}

	/**
	 * Gets the graph name.
	 *
	 * @return the graph name
	 */
	public String getGraphName() {
		return graphName;
	}

	/**
	 * Sets the graph name.
	 *
	 * @param graphName the new graph name
	 */
	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}

	/**
	 * Gets the graph type.
	 *
	 * @return the graph type
	 */
	public String getGraphType() {
		return graphType;
	}

	/**
	 * Sets the graph type.
	 *
	 * @param graphType the new graph type
	 */
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
}
