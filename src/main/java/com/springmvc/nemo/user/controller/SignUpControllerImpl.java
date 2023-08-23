package com.springmvc.nemo.user.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.user.service.SignUpService;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("joinController")
public class SignUpControllerImpl implements SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	/** 어노테이션을 이용한 방법 **/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}  

	@Override
	@RequestMapping(value = "/signup/agree", method = RequestMethod.GET)
	public ModelAndView agree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@Override
	@RequestMapping(value = "/signup/joinform", method = RequestMethod.POST)
	public ModelAndView joinForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@Override
	@RequestMapping(value = "/signup/join", method = RequestMethod.POST)
	public ModelAndView join(
			@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("birthdate=" + user.getBirthdate());
		
		signUpService.join(user);
		
		mav.setViewName("redirect:/index");
		//mav.setViewName("redirect:/login/loginform");
		mav.addObject("msg", "join");

		return mav;
		
	}

}
