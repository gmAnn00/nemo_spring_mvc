package com.springmvc.nemo.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.admin.service.AdminService;
import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.report.vo.GroupReportVO;
import com.springmvc.nemo.report.vo.UserReportVO;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("adminController")
public class AdminControllerImpl implements AdminController{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminControllerImpl.class);

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin/adminuser")
	@Override
	public ModelAndView adminUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		List<UserVO> usersList = adminService.getUsersList();
		
		mav.addObject("usersList", usersList);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/admin/adminuser/deluser", method = RequestMethod.POST)
	@Override
	public ModelAndView delUser(
			@RequestParam("user_id") String user_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		adminService.delUser(user_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		mav.addObject("data", new Message(user_id+" 회원을 삭제했습니다.", request.getContextPath()+"/admin/adminuser"));
		
		
		return mav;
	}
	
	
	@RequestMapping("/admin/admingroup")
	@Override
	public ModelAndView adminGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		List<GroupVO> groupList = adminService.getGroupList();
		
		mav.addObject("groupList", groupList);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/admin/admingroup/delgroup", method = RequestMethod.POST)
	@Override
	public ModelAndView delGroup(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		adminService.delGroup(group_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		mav.addObject("data", new Message("소모임을 삭제했습니다.", request.getContextPath()+"/admin/admingroup"));
				
		return mav;
		
	}
	


}
