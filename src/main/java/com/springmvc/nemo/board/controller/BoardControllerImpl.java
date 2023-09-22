package com.springmvc.nemo.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.board.service.BoardService;
import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;
import com.springmvc.nemo.common.Message;

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
		
		//logger.info("boardMap={}", boardMap.toString());
		
		
		String viewName = (String) request.getAttribute("viewName");
		viewName += "/board";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("boardMap", boardMap);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/group/board/viewboard", method = RequestMethod.GET)
	@Override
	public ModelAndView viewBoard(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Integer> viewMap = new HashMap<String, Integer>();
		viewMap.put("group_id", group_id);
		viewMap.put("article_no", article_no);
		
		
		BoardVO board = boardService.getBoard(article_no);
		List<CommentVO> commentsList = boardService.getCommentsList(article_no);
		boardService.updateBoardViewCnt(article_no);
		
		//logger.info("board={}", board.toString());
		//logger.info("commentsList={}", commentsList.toString());
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("board", board);
		mav.addObject("commentsList", commentsList);
		
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
	
	
	@RequestMapping(value = "/group/board/addboard", method = RequestMethod.POST)
	@Override
	public ModelAndView addBoard(
			@ModelAttribute BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		int article_no = boardService.addBoard(boardVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", new Message("새 글을 등록했습니다.",
				request.getContextPath()+"/group/board/viewboard?group_id="+boardVO.getGroup_id()+"&article_no="+article_no));
		mav.setViewName("message");
		
		return mav;
	}

}
