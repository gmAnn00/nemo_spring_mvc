package com.springmvc.nemo.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminController {
	
	public ModelAndView adminUser(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView delUser(
			@RequestParam("user_id") String user_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView adminGroup(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView delGroup(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	
}
