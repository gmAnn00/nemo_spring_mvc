package com.springmvc.nemo.group.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface GroupController {

	public ModelAndView createGroupForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity createGroup(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	public ModelAndView groupInfo(
			@RequestParam("group_id") String group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String bookmark(
			@RequestParam("user_id") String user_id, @RequestParam("group_id") String group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView joinGroup(@RequestParam("group_id") String group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView groupMain(
			@RequestParam("group_id") String group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
