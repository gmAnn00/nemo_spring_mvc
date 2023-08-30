package com.springmvc.nemo.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface MyPageController {
	
	public ModelAndView myProfile(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView modProfileForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity modProfile(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	public ResponseEntity modImage(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	public ModelAndView modInterestsForm(
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView modInterests(@RequestParam("interests") String interests,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
