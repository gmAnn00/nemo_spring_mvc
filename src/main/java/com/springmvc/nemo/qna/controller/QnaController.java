package com.springmvc.nemo.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.qna.vo.QnaVO;

public interface QnaController {
	
	
	public ModelAndView qna(
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView viewQna(
			@RequestParam(value = "qna_no") int qna_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView qnaForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView addQna(
			@ModelAttribute QnaVO qnaVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView cancelAddQna(
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView modQnaForm(
			@RequestParam("qna_no") int qna_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView modQna(
			@ModelAttribute QnaVO qnaVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView cancelModQna(
			@RequestParam("qna_no") int qna_no,
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView replyForm(
			@RequestParam("parent_no") int parent_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
			
	
}
