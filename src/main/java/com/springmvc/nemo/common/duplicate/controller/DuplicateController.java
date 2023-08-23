package com.springmvc.nemo.common.duplicate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public interface DuplicateController {
	public String idCheck(@RequestParam("user_id") String user_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String nicknameCheck(@RequestParam("nickname") String nickname,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String emailCheck(@RequestParam("emailId") String emailId, @RequestParam("emailDomain") String emailDomain,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
