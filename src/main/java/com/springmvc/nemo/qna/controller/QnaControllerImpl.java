package com.springmvc.nemo.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("qnaController")
public class QnaControllerImpl implements QnaController{
	
	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	@Override
	public ModelAndView qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		viewName += "/qna";
		mav.setViewName(viewName);
		
		return mav;
	}

}
