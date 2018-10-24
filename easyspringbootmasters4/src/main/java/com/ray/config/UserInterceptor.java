/**
 * 
 */
package com.ray.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ray
 *
 */

public class UserInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
		throws Exception{
		System.out.println("------preHandle----------");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null) {
			response.sendRedirect(request.getContextPath()+"/security/toLogin");
			return false;
		}else {
			session.setAttribute("user", session.getAttribute("user"));
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,
			ModelAndView modelAndView)throws Exception{
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
			Exception ex)throws Exception{
		
	}
}
