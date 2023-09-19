package com.springmvc.nemo.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.board.service.BoardService;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardControllerImpl.class);
	
	
	@RequestMapping(value = "/group/board", method = RequestMethod.GET)
	@Override
	public ModelAndView board(
			@RequestParam("group_id") int group_id,
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		Map<String, Object> pagingMap = new HashMap<String, Object>();
		pagingMap.put("section", section);
		pagingMap.put("pagenum", pagenum);
		pagingMap.put("group_id", group_id);
		
		Map<String, Object> boardMap = boardService.listBoard(pagingMap);
		boardMap.put("section", section);
		boardMap.put("pagenum", pagenum);
		
		
		String viewName = (String) request.getAttribute("viewName");
		viewName += "/board";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("boardMap", boardMap);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/group/board/boardform", method = RequestMethod.GET)
	@Override
	public ModelAndView boardForm(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		return mav;
	}

}
