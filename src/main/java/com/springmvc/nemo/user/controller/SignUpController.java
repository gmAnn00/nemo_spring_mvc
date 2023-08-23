package com.springmvc.nemo.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.user.vo.UserVO;

public interface SignUpController {
	
	public ModelAndView agree(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView joinForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView join(
			@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView interestsForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView interests(@RequestParam("interests") String interests, HttpServletRequest request, HttpServletResponse response) throws Exception;

	

}
