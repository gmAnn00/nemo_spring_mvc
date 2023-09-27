package com.springmvc.nemo.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface QnaController {
	public ModelAndView qna(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
