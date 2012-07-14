package com.odb.view.core;

import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.icepush.PushContext;
import org.springframework.web.context.WebApplicationContext;

import com.odb.core.SubscriberInfo;
import com.odb.core.service.OpenDashBoard;
import com.odb.view.dashboard.client.Dashboard;
import com.odb.view.dashboard.client.DashboardService;
import com.odb.view.util.Utilities;

/**
 * The Class ActionProcessor.
 * 
 * This class is the delegation class for the {@link DashboardController} to handle each request and decide which navigation is next
 * 
 * this class receives a reference of the {@link OpenDashBoard} service to call the perform the system functions.
 * 
 */
public class ActionProcessor {

	/** The log. */
	private static Logger log = Logger.getLogger(ActionProcessor.class);
	
	/** The odb core. */
	private OpenDashBoard odbCore;
	
	/**
	 * Instantiates a new action processor.
	 *
	 * @param applicationContext the application context
	 */
	public ActionProcessor(WebApplicationContext applicationContext) {
		this.odbCore=(OpenDashBoard)applicationContext.getBean("OpenDashBoardCore");
	}

	/**
	 * Execute action.
	 * 
	 * this is the main method for the {@link ActionProcessor} class, 
	 * it takes an action string and then decide which action to perform.
	 *
	 * @param action the action string
	 * @param request the httprequest
	 * @param response the httpresponse
	 * @return a string used to decide which navigation is next
	 */
	public String executeAction(String action, HttpServletRequest request, HttpServletResponse response){
		if("login".equals(action)){
			return loginAction(request, response);
		}else if("publish".equals(action)){
			return firePushEventAction(request, response);
		}
		return null;
	}

	/**
	 * Fire push event action for the given dataSourceId.
	 * 
	 * this causes the {@link DashboardService#getDataUpdate(String, String)} to be called for the specified datasourceId 
	 * and causes the Chart to be updated with the new data
	 *
	 * @param request the request
	 * @param response the response
	 * @return a string used to decide which navigation is next
	 * 
	 * @see OpenDashBoard#publish(String)
	 * @see Dashboard#onModuleLoad()
	 */
	public String firePushEventAction(HttpServletRequest request,
			HttpServletResponse response) {
		String dataSourceId = null;
		try {
			dataSourceId= request.getParameter("dataSourceId");
			PushContext pushContext = PushContext.getInstance(request.getSession().getServletContext());
			pushContext.push(dataSourceId);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			log.error("error while firing Push Event for dataSourceId: " + dataSourceId, e);
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		return null;
	}

	/**
	 * Login action.
	 *
	 * @param request the request
	 * @param response the response
	 * @return a string used to decide which navigation is next
	 */
	public String loginAction(HttpServletRequest request,
			HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SubscriberInfo subscriberInfo;
		try {
			subscriberInfo = odbCore.subscriberLogin(username, Utilities.encrypt(password));
			if(subscriberInfo == null){
				return "loginError";
			}else{
				request.getSession().setAttribute("subscriberInfo", subscriberInfo);
			}
		} catch (GeneralSecurityException e) {
			log.error("", e);
			return "loginError";
		} catch (Exception e) {
			log.error("", e);
			return "loginError";
		}
		return "Dashboard";
	}
}
