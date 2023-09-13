package com.springmvc.nemo.member.controller;

import java.util.List;

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
import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.group.vo.WaitListVO;
import com.springmvc.nemo.member.service.MemberService;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value = "/group/member", method = RequestMethod.GET)
	@Override
	public ModelAndView groupMember(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		JoinVO joinVO = new JoinVO();
		joinVO.setUser_id(user_id);
		joinVO.setGroup_id(group_id);
		
		UserVO user = memberService.getUserInfo(user_id);
		List<UserVO> membersList = memberService.getMemberInfo(joinVO);
		
		String viewName = (String) request.getAttribute("viewName");
		viewName += "/member";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("user", user);
		mav.addObject("membersList", membersList);
		
		
		return mav;
	}
	
	@RequestMapping(value = "/group/member/cancelgroup", method = RequestMethod.GET)
	@Override
	public ModelAndView cancelGroup(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		JoinVO joinVO = new JoinVO();
		joinVO.setGroup_id(group_id);
		joinVO.setUser_id(user_id);
		
		boolean isAlreadyCancelResult = memberService.isAlreadyCancel(joinVO);
		
		boolean isGroupLeader = memberService.isGroupLeader(joinVO);
		
		ModelAndView mav = new ModelAndView();
		
		if(isAlreadyCancelResult) {
			mav.addObject("data", new Message("이미 탈퇴한 소모임입니다.", request.getContextPath()+"/mypage/mygroup"));
		} else if(!isAlreadyCancelResult && isGroupLeader) {
			mav.addObject("data", new Message("소모임 리더는 소모임을 탈퇴할 수 없습니다.", request.getContextPath()+"/group/groupmain?group_id="+group_id));
		} else {
			memberService.cancelGroup(joinVO);
			mav.addObject("data", new Message("소모임을 탈퇴하였습니다.", request.getContextPath()+"/mypage/mygroup"));
		}

		mav.setViewName("message");
		
		return mav;
	}
	
	@RequestMapping(value = "/group/member/cancelwait", method = RequestMethod.GET)
	@Override
	public ModelAndView cancelWait(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		WaitListVO waitListVO = new WaitListVO();
		waitListVO.setGroup_id(group_id);
		waitListVO.setUser_id(user_id);
		
		memberService.cancelWait(waitListVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", new Message("소모임 가입 신청을 취소하였습니다.", request.getContextPath()+"/mypage/mygroup"));
		mav.setViewName("message");
		
		return mav;
	}

}
