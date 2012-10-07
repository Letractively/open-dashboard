/*******************************************************************************
 * Copyright (c) 2012, Nabeel Shaheen	
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 ******************************************************************************/
package com.odb.view.core;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class DashboardController.
 * 
 * this class is performing the Controller role of the dashboard MVC design
 * 
 * 
 * it catches all requests and then forwards the processing to the class 
 * {@link com.odb.view.core.ActionProcessor }. and then it receives the information needed to decide 
 * the next view.
 * <p>
 * 
 * this class accepts both GET and POST and process them in the same way 
 * in the {@link #processRequest(HttpServletRequest, HttpServletResponse)}.
 */
public class DashboardController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log = Logger.getLogger(DashboardController.class);
	
	/** The application context. */
	private WebApplicationContext applicationContext;
    
    /** The action processor. */
    private ActionProcessor actionProcessor;
    
    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    	actionProcessor=new ActionProcessor(applicationContext);
    	log.info("DashboardController initialized with WebApplicationContext...");
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Process POST and GET requests.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action=getActionString(request);
		String location=null;
		if(action !=null){
			location=actionProcessor.executeAction(action, request, response);
		}
		if(location!=null){
			request.getRequestDispatcher(getLocationString(location)).forward(request, response);
		}
	}

	/**
	 * Gets the location string.
	 *
	 * @param location the location
	 * @return the location string
	 */
	private String getLocationString(String location) {
		if(location.equals("loginError")){
			return "/loginError.html";
		}else if(location.equals("Dashboard")){
			return "/Dashboard.html";
		}
		return null;
	}

	/**
	 * Gets the action string.
	 *
	 * @param request the request
	 * @return the action string
	 */
	private String getActionString(HttpServletRequest request) {
		String reqUrl = request.getRequestURL().toString();
		return reqUrl.substring(reqUrl.lastIndexOf("/")+1, reqUrl.lastIndexOf("."));
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
