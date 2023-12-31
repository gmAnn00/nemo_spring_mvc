package com.springmvc.nemo.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;

public interface BoardController {
	
	public ModelAndView board(
			@RequestParam("group_id") int group_id,
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView viewBoard(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView boardForm(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView addBoard(
			@ModelAttribute BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView cancelAddBoard(
			@RequestParam("group_id") int group_id,
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView modBoardForm(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView modBoard(
			@ModelAttribute BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView cancelModBoard(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView delboard(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView boardSeach(
			@RequestParam("group_id") int group_id,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public String addComment(
			@RequestParam("group_id") int group_id,
			@ModelAttribute CommentVO commentVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public String addReply(
			@RequestParam("group_id") int group_id,
			@ModelAttribute CommentVO commentVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public String modComment(
			@RequestParam("group_id") int group_id,
			@ModelAttribute CommentVO commentVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public String cancelMod(
			@RequestParam("group_id") int group_id,
			@RequestParam("comment_no") int comment_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView delComment(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			@RequestParam("comment_no") int comment_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;


}
