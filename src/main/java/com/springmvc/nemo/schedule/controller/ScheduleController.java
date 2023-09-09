package com.springmvc.nemo.schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.schedule.vo.ScheduleVO;

public interface ScheduleController {
	
	public ModelAndView schedule(
			@RequestParam("group_id") String str_group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String ajaxCalendar(
			@RequestParam("group_id") String str_group_id,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String checkSchedule(
			@RequestParam("group_id") String str_group_id,
			@RequestParam("selScheDate") String selScheDate,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView addSchedule(
			@RequestParam("group_id") String str_group_id,
			@ModelAttribute("scheduleVO") ScheduleVO scheduleVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
			
}
