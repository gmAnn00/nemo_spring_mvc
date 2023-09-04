package com.springmvc.nemo.schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ScheduleController {
	
	public ModelAndView schedule(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
