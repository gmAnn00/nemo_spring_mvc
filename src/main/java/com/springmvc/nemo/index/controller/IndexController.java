package com.springmvc.nemo.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface IndexController {
	
	public ModelAndView defaultURL(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception;


}
