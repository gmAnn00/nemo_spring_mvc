package com.springmvc.nemo.leader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface LeaderController {
	
	public ModelAndView memberInfo(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView mandate(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView exile(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView approve(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView reject(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView settingForm(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ResponseEntity modGroupSetting(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView delGroupForm(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView delGroup(
			@RequestParam("group_id") int group_id,
			@RequestParam("delString") String delString,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
