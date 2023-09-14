package com.springmvc.nemo.index.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.index.service.IndexService;

@Controller("indexController")
public class IndexControllerImpl implements IndexController{
	
	@Autowired
	private IndexService indexService;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Override
	public ModelAndView defaultURL(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index");

		return mav;
	}

	@Override
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		HttpSession session = request.getSession(false);
		String user_id = null;
		
		if(session != null) {
			user_id = (String) session.getAttribute("user_id");
			
		}
		
		if(user_id == null) {
			// 로그인 안함
			List<GroupVO> randomGroupList = new ArrayList<GroupVO>();
			randomGroupList = indexService.getRandomGroupList();
			mav.addObject("randomGroupList", randomGroupList);
			
			
		}else {
			// 로그인 함
			List<GroupVO> interestGroupList = new ArrayList<GroupVO>();
			interestGroupList = indexService.getInterestGroupList(user_id);
			
			List<GroupVO> nearGroupList = new ArrayList<GroupVO>();
			nearGroupList = indexService.getNearGroupList(user_id);			
			
			mav.addObject("interestGroupList", interestGroupList);
			mav.addObject("nearGroupList", nearGroupList);
			
		}

		return mav;
	}


}
