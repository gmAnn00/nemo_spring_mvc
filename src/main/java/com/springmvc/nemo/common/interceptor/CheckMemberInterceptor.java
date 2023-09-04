package com.springmvc.nemo.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.group.vo.JoinVO;

public class CheckMemberInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(CheckMemberInterceptor.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("checkMem");
		HttpSession session = request.getSession(false);
		String user_id = (String) session.getAttribute("user_id");
		String str_group_id = request.getParameter("group_id");
		int group_id = Integer.parseInt(str_group_id);
		
		JoinVO joinVO = new JoinVO();
		joinVO.setGroup_id(group_id);
		joinVO.setUser_id(user_id);
		
		boolean isMemberResult = sqlSession.selectOne("mapper.group.isGroupMember", joinVO);
		
		if(!isMemberResult) {
			request.setAttribute("data", new Message("소모임 가입 후 이용할 수 있습니다.", request.getContextPath() + "/group/groupinfo?group_id="+str_group_id));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			rd.forward(request, response);
			return false;
		}
		
		return true;
	}
	
}
