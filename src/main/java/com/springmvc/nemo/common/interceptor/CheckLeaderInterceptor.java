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
import com.springmvc.nemo.group.vo.GroupVO;

public class CheckLeaderInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(CheckLeaderInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//logger.info("checkLeader");
		HttpSession session = request.getSession(false);
		String user_id = (String) session.getAttribute("user_id");
		String str_group_id = request.getParameter("group_id");
		int group_id = Integer.parseInt(str_group_id);
		
		GroupVO groupVO = sqlSession.selectOne("mapper.group.getGroupInfo", group_id);
		String group_leader = groupVO.getGroup_leader();
		boolean isLeaderResult = user_id.equals(group_leader);
		request.setAttribute("isLeader", isLeaderResult);
		//logger.info("isLeader={}", isLeaderResult);
		
		String servletPath = request.getServletPath();
		boolean isLeaderURL = servletPath.contains("/leader");
		
		if(!isLeaderResult && isLeaderURL) {
			request.setAttribute("data", new Message("소모임 리더만 이용할 수 있습니다.", request.getContextPath() + "/group/groupmain?group_id="+str_group_id));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
			rd.forward(request, response);
			return false;
		}
		return true;
	}

}
