package com.springmvc.nemo.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.user.vo.UserVO;

public interface LoginController {
	
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView loginTry(@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView findIdForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView findId(@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView resetPasswordCheckForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView resetPasswordCheck(@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView resetPasswordForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView resetPassword(@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
}
