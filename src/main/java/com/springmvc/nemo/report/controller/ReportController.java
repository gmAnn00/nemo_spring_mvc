package com.springmvc.nemo.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface ReportController {
	
	public ModelAndView reportGroup(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView reportMember(
			@RequestParam("group_id") int group_id,
			@RequestParam("accused_id") String accused_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
