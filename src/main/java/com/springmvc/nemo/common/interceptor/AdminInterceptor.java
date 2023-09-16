package com.springmvc.nemo.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.nemo.common.Message;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		int admin = (Integer) session.getAttribute("admin");
		
		if(admin == 0) {
			
			request.setAttribute("data", new Message("관리자만 이용할 수 있습니다.", request.getContextPath() + "/index"));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			rd.forward(request, response);
			
			return false;
		}
		
		return true;
	}

}
