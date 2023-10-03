package com.springmvc.nemo.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.springmvc.nemo.user.service.LoginService;
import com.springmvc.nemo.user.vo.UserVO;

public class KeepLoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			UserVO userVO = loginService.getUserBySessionId(loginCookie.getValue());
			
			if(userVO != null) {
				session.setAttribute("user_id", userVO.getUser_id());
				session.setAttribute("nickname", userVO.getNickname());
				session.setAttribute("user_img", userVO.getUser_img());
				session.setAttribute("admin", userVO.getAdmin());
				session.setAttribute("sns", 0);
			}
		}
		
		
		return true;
	}

}
