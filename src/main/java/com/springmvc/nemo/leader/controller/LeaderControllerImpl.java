package com.springmvc.nemo.leader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.leader.service.LeaderService;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("leaderController")
public class LeaderControllerImpl implements LeaderController{
	
	private static final Logger logger = LoggerFactory.getLogger(LeaderControllerImpl.class);
	
	@Autowired
	private LeaderService leaderService;
	
	@RequestMapping(value = "/group/leader/memberinfo")
	@Override
	public ModelAndView memberInfo(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");

		UserVO user = leaderService.getUserInfo(user_id);
		
		List<UserVO> membersList = leaderService.getMemberInfo(group_id);
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("user", user);
		mav.addObject("membersList", membersList);
		
		return mav;
	}

}
