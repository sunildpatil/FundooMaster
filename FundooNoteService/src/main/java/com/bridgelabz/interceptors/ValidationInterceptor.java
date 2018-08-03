package com.bridgelabz.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.utility.TokenHelper;

@PropertySource("classpath:error.properties")
public class ValidationInterceptor implements HandlerInterceptor  {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@Autowired
	private Environment environment;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("preHandle : Method : "+request.getMethod() + " URL : "+request.getRequestURI());
			
		System.out.println("Method : "+request.getMethod());
		
		if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
			
			return true;
		}
		
		int id = Integer.parseInt(tokenHelper.parseJWT(request.getHeader(environment.getProperty("headername"))));
				
		request.setAttribute("userID", id);
							
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info("postHandle Status : "+response.getStatus());
//		request.removeAttribute("userID");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}	
}