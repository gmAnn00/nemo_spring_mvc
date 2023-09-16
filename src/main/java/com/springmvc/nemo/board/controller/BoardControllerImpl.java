package com.springmvc.nemo.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	
	
	@RequestMapping(value = "/group/board")
	@Override
	public ModelAndView board(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		return null;
	}

}
