package com.odb.view.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * The Class SecurityFilter.
 *
 * this class acts as a simple security guard for the Dashboard view module. it passes all the 
 * request that has a session attribute with the key subscriberInfo. plus, all the anonymous URLs like login page. 
 *   
 * if the request is not authenticated it will be directed to the login page.
 *  
 */
public class SecurityFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String reqURL=((HttpServletRequest)request).getRequestURL().toString();
		if(request.getSession().getAttribute("subscriberInfo")!=null || isAnonymousURL(reqURL) ){
			filterChain.doFilter(req, res);
		}else{
			request.getRequestDispatcher("/login.html").forward(req, res);
		}
	}

	private boolean isAnonymousURL(String reqURL) {
		if(reqURL.endsWith("login.html") || reqURL.endsWith("loginError.html") || reqURL.endsWith("login.action") || reqURL.endsWith("publish.action") || reqURL.endsWith("error.html") ){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
	}

}
