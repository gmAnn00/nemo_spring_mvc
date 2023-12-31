package com.springmvc.nemo.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.springmvc.nemo.board.service.BoardService;
import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;
import com.springmvc.nemo.common.Message;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardControllerImpl.class);
	private static String BOARD_IMG_DIR;
	
	
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
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		boardVO.setUser_id(user_id);
		
		int article_no = boardService.addBoard(boardVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", new Message("새 글을 등록했습니다.",
				request.getContextPath()+"/group/board/viewboard?group_id="+boardVO.getGroup_id()+"&article_no="+article_no));
		mav.setViewName("message");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/group/board/canceladdboard", method = RequestMethod.POST)
	@Override
	public ModelAndView cancelAddBoard(
			@RequestParam("group_id") int group_id,
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(isImgExist) {
			deleteTempImg(imageName);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/group/board?group_id="+group_id);
		return mav;
	}
	

	@RequestMapping(value = "/group/board/modboardform", method = RequestMethod.GET)
	@Override
	public ModelAndView modBoardForm(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardVO board = boardService.getBoard(article_no);
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		ModelAndView mav = new ModelAndView();
		
		if(!user_id.equals(board.getUser_id())) {
			mav.setViewName("message");
			mav.addObject("data", new Message("글쓴이만 글을 수정할 수 있습니다.",
					request.getContextPath()+"/group/board/viewboard?group_id="+group_id+"&article_no="+article_no));
			return mav;
		}
		
		
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		mav.addObject("board", board);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/group/board/modboard", method = RequestMethod.POST)
	@Override
	public ModelAndView modBoard(
			@ModelAttribute BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		
		
		if(!user_id.equals(boardVO.getUser_id())) {
			mav.addObject("data", new Message("글쓴이만 글을 수정할 수 있습니다.",
					request.getContextPath()+"/group/board/viewboard?group_id="+boardVO.getGroup_id()+"&article_no="+boardVO.getArticle_no()));
			return mav;
		}
		
		boardService.modBoard(boardVO);
		mav.addObject("data", new Message("글을 수정했습니다.",
				request.getContextPath()+"/group/board/viewboard?group_id="+boardVO.getGroup_id()+"&article_no="+boardVO.getArticle_no()));
		
		
		return mav;

	}
	
	@RequestMapping(value = "/group/board/cancelmodboard", method = RequestMethod.POST)
	@Override
	public ModelAndView cancelModBoard(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(isImgExist) {
			deleteTempImg(imageName);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/group/board/viewboard?group_id="+group_id+"&article_no="+article_no);
		return mav;
		
	}
	
	
	@RequestMapping(value = "/group/board/delboard", method = RequestMethod.GET)
	@Override
	public ModelAndView delboard(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		BoardVO board = boardService.getBoard(article_no);
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		if(user_id.equals(board.getUser_id())) {
			
			boardService.delBoard(article_no);
			
			mav.addObject("data", new Message("글을 삭제했습니다.",
					request.getContextPath()+"/group/board?group_id="+group_id));
			
			
		}else {
			mav.addObject("data", new Message("글쓴이만 글을 삭제할 수 있습니다.",
					request.getContextPath()+"/group/board/viewboard?group_id="+group_id+"&article_no="+article_no));
		}
		
		return mav;
		
	}
	
	
	@RequestMapping(value = "/group/board/boardsearch", method = RequestMethod.GET)
	@Override
	public ModelAndView boardSeach(
			@RequestParam("group_id") int group_id,
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("filter", filter);
		searchMap.put("keyword", keyword);
		searchMap.put("section", section);
		searchMap.put("pagenum", pagenum);
		searchMap.put("group_id", group_id);
		
		
		Map<String, Object> boardMap = boardService.searchBoard(searchMap);
		boardMap.put("filter", filter);
		boardMap.put("keyword", keyword);
		boardMap.put("section", section);
		boardMap.put("pagenum", pagenum);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/group/board/board");
		mav.addObject("boardMap", boardMap);
		
		return mav;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/group/board/addcomment", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	@Override
	public String addComment(
			@RequestParam("group_id") int group_id,
			@ModelAttribute CommentVO commentVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		commentVO.setUser_id(user_id);
		
		CommentVO comment = boardService.addComment(commentVO);
		
		Gson gson = new Gson();
		String commentInfo = gson.toJson(comment);
		
		//logger.info("commentInfo={}", commentInfo);
		
		return commentInfo;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/group/board/addreply", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	@Override
	public String addReply(
			@RequestParam("group_id") int group_id,
			@ModelAttribute CommentVO commentVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		commentVO.setUser_id(user_id);
		
		CommentVO comment = boardService.addComment(commentVO);
		
		Gson gson = new Gson();
		String commentInfo = gson.toJson(comment);
		
		//logger.info("commentInfo={}", commentInfo);
		
		return commentInfo;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/group/board/modcomment", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	@Override
	public String modComment(
			@RequestParam("group_id") int group_id,
			@ModelAttribute CommentVO commentVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		commentVO.setUser_id(user_id);

		return boardService.modComment(commentVO);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/group/board/cancelmod", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	@Override
	public String cancelMod(
			@RequestParam("group_id") int group_id,
			@RequestParam("comment_no") int comment_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return boardService.getContentByCommentNo(comment_no);
	}
	
	
	@RequestMapping(value = "/group/board/delcomment", method = RequestMethod.GET)
	@Override
	public ModelAndView delComment(
			@RequestParam("group_id") int group_id,
			@RequestParam("article_no") int article_no,
			@RequestParam("comment_no") int comment_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		String original_user_id = boardService.getUserIdByCommentNo(comment_no);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		if(!user_id.equals(original_user_id)) {
			
			mav.addObject("data", new Message("댓글을 쓴 사용자만 댓글을 삭제할 수 있습니다.",
					request.getContextPath()+"/group/board/viewboard?group_id="+group_id+"&article_no="+article_no));
			
			return mav;
		}
		
		CommentVO commentVO = new CommentVO();
		commentVO.setArticle_no(article_no);
		commentVO.setComment_no(comment_no);
		
		boardService.delComment(commentVO);
		mav.addObject("data", new Message("댓글을 삭제하였습니다.",
				request.getContextPath()+"/group/board/viewboard?group_id="+group_id+"&article_no="+article_no));
		
		return mav;
	}
	
	
	
	
	private void deleteTempImg(String[] imageName) {
		try {
			BOARD_IMG_DIR=this.getClass().getResource("").getPath();
			BOARD_IMG_DIR=BOARD_IMG_DIR.substring(1,BOARD_IMG_DIR.indexOf(".metadata"));
			BOARD_IMG_DIR=BOARD_IMG_DIR.replace("/", "\\");
			BOARD_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\boardImages\\temp\\";

			if(imageName!=null && imageName.length !=0) {
				for(String imgName:imageName) {
					File srcFile=new File(BOARD_IMG_DIR+imgName);
					srcFile.delete();
				}	
			}
			
		} catch (Exception e) {
			logger.info("이미지 삭제 중 에러");
			e.printStackTrace();
		}
	}
		

}
