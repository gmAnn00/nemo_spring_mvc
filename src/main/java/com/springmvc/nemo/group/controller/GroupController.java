package com.springmvc.nemo.group.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.group.vo.GroupVO;

public interface GroupController {

	public ModelAndView createGroupForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity createGroup(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	
	
}
