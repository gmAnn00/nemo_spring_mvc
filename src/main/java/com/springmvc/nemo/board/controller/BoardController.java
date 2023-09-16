package com.springmvc.nemo.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface BoardController {
	
	public ModelAndView board(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
