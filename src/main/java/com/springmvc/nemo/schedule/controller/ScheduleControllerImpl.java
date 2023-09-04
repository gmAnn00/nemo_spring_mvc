package com.springmvc.nemo.schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("scheduleController")
public class ScheduleControllerImpl implements ScheduleController{
	
	@RequestMapping(value = "/group/schedule", method = RequestMethod.GET)
	@Override
	public ModelAndView schedule(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		viewName += "/schedule";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

}
