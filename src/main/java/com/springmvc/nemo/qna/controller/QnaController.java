package com.springmvc.nemo.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface QnaController {
	
	
	public ModelAndView qna(
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView viewQna(
			@RequestParam(value = "qna_no") int qna_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}
