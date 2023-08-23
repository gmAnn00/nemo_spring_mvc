package com.springmvc.nemo.common.duplicate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.nemo.common.duplicate.service.DuplicateService;

@Controller("duplicateController")
public class DuplicateControllerImpl implements DuplicateController{
	
	@Autowired
	private DuplicateService duplicateService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/duplicate/id", method = RequestMethod.POST)
	public String idCheck(@RequestParam("user_id") String user_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("duplicateController");
		return duplicateService.idCheck(user_id);
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/duplicate/nickname", method = RequestMethod.POST)
	public String nicknameCheck(@RequestParam("nickname") String nickname,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return duplicateService.nicknameCheck(nickname);
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/duplicate/email", method = RequestMethod.POST)
	public String emailCheck(@RequestParam("emailId") String emailId, @RequestParam("emailDomain") String emailDomain,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String email = emailId + "@" + "emailDomain";
		
		return duplicateService.emailCheck(email);
	}
	
	

}
