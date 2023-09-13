package com.springmvc.nemo.leader.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.leader.service.LeaderService;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("leaderController")
public class LeaderControllerImpl implements LeaderController{
	
	private static final Logger logger = LoggerFactory.getLogger(LeaderControllerImpl.class);
	
	@Autowired
	private LeaderService leaderService;
	
	@RequestMapping(value = "/group/leader/memberinfo", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/group/leader/mandate", method = RequestMethod.GET)
	@Override
	public ModelAndView mandate(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> mandateMap = new HashMap<String, Object>();
		mandateMap.put("group_id", group_id);
		mandateMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		String msg = leaderService.mandateLeader(mandateMap) + "님에게 소모임 리더를 위임했습니다.";
		mav.addObject("data", new Message(msg, request.getContextPath()+"/group/groupmain?group_id="+group_id));
		
		return mav;
	}

}
