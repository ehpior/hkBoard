package com.hk.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hk.test.dto.AccountDto;
import com.hk.test.util.CommUtil;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info(CommUtil.getClientIP(request)+":preHandle");
		
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("login");
		
		if(obj == null) {
			response.sendRedirect(request.getContextPath()+"/login.hk");
			return false;
		}
		else if(((AccountDto)obj).getBlock() == ("X")){
			logger.info("blocked");
			response.sendRedirect(request.getContextPath()+"/home.hk");
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info(CommUtil.getClientIP(request)+":postHandle");
		

	}
	
}
