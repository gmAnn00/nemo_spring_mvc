package com.springmvc.nemo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.nemo.group.vo.GroupVO;

public class GroupHeaderInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		
		GroupVO groupHeader = sqlSession.selectOne("mapper.group.getGroupInfo", group_id);
		request.setAttribute("groupHeader", groupHeader);
		
		return true;
	}
	
}
