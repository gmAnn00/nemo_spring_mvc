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
		List<UserVO> waitUsersList = leaderService.getWaitUserInfo(group_id);
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("user", user);
		mav.addObject("membersList", membersList);
		mav.addObject("waitUsersList", waitUsersList);
		
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
		
		boolean isGroupMemberResult = leaderService.isGroupMember(mandateMap);
		
		if(isGroupMemberResult) {
			String msg = leaderService.mandateLeader(mandateMap) + "님에게 소모임 리더를 위임했습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/groupmain?group_id="+group_id));
		}else {
			mav.addObject("data", new Message("해당 회원은 소모임 멤버가 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
		}
		
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/group/leader/exile", method = RequestMethod.GET)
	@Override
	public ModelAndView exile(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> exileMap = new HashMap<String, Object>();
		exileMap.put("group_id", group_id);
		exileMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		boolean isGroupMemberResult = leaderService.isGroupMember(exileMap);
		
		if(isGroupMemberResult) {
			String msg = leaderService.exileMember(exileMap) + "님을 소모임에서 추방했습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
		} else {
			mav.addObject("data", new Message("해당 회원은 소모임 멤버가 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/group/leader/approve", method = RequestMethod.GET)
	@Override
	public ModelAndView approve(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> approveMap = new HashMap<String, Object>();
		approveMap.put("group_id", group_id);
		approveMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		boolean isWaitResult = leaderService.isWait(approveMap);
		
		if(isWaitResult) {
			
			String msg = leaderService.approveMember(approveMap) + "님의 소모임 가입을 승인하였습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}else {
			
			mav.addObject("data", new Message("해당 회원은 가입 대기 중이 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/group/leader/reject", method = RequestMethod.GET)
	@Override
	public ModelAndView reject(int group_id, String target_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Map<String, Object> rejectMap = new HashMap<String, Object>();
		rejectMap.put("group_id", group_id);
		rejectMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		boolean isWaitResult = leaderService.isWait(rejectMap);
		
		if(isWaitResult) {
			
			String msg = leaderService.rejectMember(rejectMap) + "님의 소모임 가입을 거절하였습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}else {
			
			mav.addObject("data", new Message("해당 회원은 가입 대기 중이 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}

		return mav;
		
		
	}

}
