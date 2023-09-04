package com.springmvc.nemo.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.nemo.common.Message;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("user_id") == null) {
			request.setAttribute("data", new Message("로그인이 필요합니다.", request.getContextPath() + "/login/loginform"));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			rd.forward(request, response);
			return false;
		}
		
		return true;
	}

}
