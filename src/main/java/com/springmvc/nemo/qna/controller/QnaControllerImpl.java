package com.springmvc.nemo.qna.controller;

import java.io.File;
import java.util.HashMap;
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

import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.qna.service.QnaService;
import com.springmvc.nemo.qna.vo.QnaVO;

@Controller("qnaController")
public class QnaControllerImpl implements QnaController{
	
	private static final Logger logger = LoggerFactory.getLogger(QnaControllerImpl.class);
	
	private static String QNA_IMG_DIR;
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	@Override
	public ModelAndView qna(
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		int admin = (Integer) session.getAttribute("admin");
		
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		viewName += "/qna";
		mav.setViewName(viewName);
		
		Map<String, Object> pagingMap = new HashMap<String, Object>();
		pagingMap.put("section", section);
		pagingMap.put("pagenum", pagenum);
		pagingMap.put("user_id", user_id);
		pagingMap.put("admin", admin);
		
		Map<String, Object> qnaMap = qnaService.listQna(pagingMap);
		qnaMap.put("section", section);
		qnaMap.put("pagenum", pagenum);
		
		mav.addObject("qnaMap", qnaMap);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/viewqna", method = RequestMethod.GET)
	@Override
	public ModelAndView viewQna(
			@RequestParam(value = "qna_no") int qna_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		int admin = (Integer) session.getAttribute("admin");
		
		ModelAndView mav = new ModelAndView();
		
		QnaVO qna = qnaService.getQna(qna_no);
		
		Map<String, Object> accessMap = new HashMap<String, Object>();
		accessMap.put("user_id", user_id);
		accessMap.put("qna_no", qna_no);
		
		boolean accessible = qnaService.getAccessible(accessMap);
		
		if(admin == 0 && !user_id.equals(qna.getUser_id()) && !accessible) {
			mav.setViewName("message");
			mav.addObject("data", new Message("해당 문의사항을 볼 수 없습니다.", request.getContextPath() + "/qna"));
			
			return mav;
		}
		
		
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		mav.addObject("qna", qna);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/qnaform", method = RequestMethod.GET)
	@Override
	public ModelAndView qnaForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/addqna", method = RequestMethod.POST)
	@Override
	public ModelAndView addQna(QnaVO qnaVO, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		qnaVO.setUser_id(user_id);
		
		logger.info("qnaVO={}",qnaVO.toString());
		
		int qna_no = qnaService.addQna(qnaVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		if(qnaVO.getParent_no() == 0) {
			mav.addObject("data", new Message("새 문의사항을 등록했습니다.",
					request.getContextPath()+"/qna/viewqna?qna_no="+qna_no));
		} else {
			mav.addObject("data", new Message("새 답변을 등록했습니다.",
					request.getContextPath()+"/qna/viewqna?qna_no="+qna_no));
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/canceladdqna", method = RequestMethod.POST)
	@Override
	public ModelAndView cancelAddQna(
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(isImgExist) {
			deleteTempImg(imageName);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/qna");
		return mav;
		
	}
	
	
	@RequestMapping(value = "/qna/modqnaform", method = RequestMethod.GET)
	@Override
	public ModelAndView modQnaForm(
			@RequestParam("qna_no") int qna_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		QnaVO qna = qnaService.getQna(qna_no);
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		ModelAndView mav = new ModelAndView();
		
		if(!user_id.equals(qna.getUser_id())) {
			mav.setViewName("message");
			mav.addObject("data", new Message("글쓴이만 글을 수정할 수 있습니다.",
					request.getContextPath()+"/qna"));
			return mav;
		}
		
		
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		mav.addObject("qna", qna);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/modqna", method = RequestMethod.POST)
	@Override
	public ModelAndView modQna(
			@ModelAttribute QnaVO qnaVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");

		if(!user_id.equals(qnaVO.getUser_id())) {
			mav.addObject("data", new Message("글쓴이만 문의사항을 수정할 수 있습니다.",
					request.getContextPath()+"/qna"));
			return mav;
		}
		
		qnaService.modQna(qnaVO);
		mav.addObject("data", new Message("문의사항을 수정했습니다.",
				request.getContextPath()+"/qna/viewqna?&qna_no="+qnaVO.getQna_no()));
		
		
		return mav;

	}
	
	
	@RequestMapping(value = "/qna/cancelmodqna", method = RequestMethod.POST)
	@Override
	public ModelAndView cancelModQna(
			@RequestParam("qna_no") int qna_no,
			@RequestParam("isImgExist") boolean isImgExist,
			@RequestParam(value = "imageName", required = false) String[] imageName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(isImgExist) {
			deleteTempImg(imageName);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/qna/viewqna?&qna_no="+qna_no);
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/replyform", method=RequestMethod.GET)
	@Override
	public ModelAndView replyForm(
			@RequestParam("parent_no") int parent_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		int admin = (Integer) session.getAttribute("admin");
		
		ModelAndView mav = new ModelAndView();
		if(admin == 1) {
			String viewName = (String) request.getAttribute("viewName");
			mav.setViewName(viewName);

		} else {
			mav.setViewName("message");
			mav.addObject("data", new Message("관리자만 답변을 작성할 수 있습니다.", request.getContextPath()+"/qna"));
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/delqna", method = RequestMethod.GET)
	@Override
	public ModelAndView delQna(
			@RequestParam("qna_no") int qna_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		QnaVO qna = qnaService.getQna(qna_no);
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		if(user_id.equals(qna.getUser_id())) {
			
			qnaService.delQna(qna_no);
			
			mav.addObject("data", new Message("문의사항을 삭제했습니다.",
					request.getContextPath()+"/qna"));
			
			
		}else {
			mav.addObject("data", new Message("글쓴이만 문의사항을 삭제할 수 있습니다.",
					request.getContextPath()+"/qna"));
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/qna/qnasearch", method = RequestMethod.GET)
	@Override
	public ModelAndView qnaSearch(
			@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		int admin = (Integer) session.getAttribute("admin");
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("filter", filter);
		searchMap.put("keyword", keyword);
		searchMap.put("section", section);
		searchMap.put("pagenum", pagenum);
		searchMap.put("user_id", user_id);
		searchMap.put("admin", admin);
		
		
		Map<String, Object> qnaMap = qnaService.searchQna(searchMap);
		qnaMap.put("filter", filter);
		qnaMap.put("keyword", keyword);
		qnaMap.put("section", section);
		qnaMap.put("pagenum", pagenum);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/qna/qna");
		mav.addObject("qnaMap", qnaMap);
		
		return mav;
		
	}
	
	
	
	private void deleteTempImg(String[] imageName) {
		try {
			QNA_IMG_DIR=this.getClass().getResource("").getPath();
			QNA_IMG_DIR=QNA_IMG_DIR.substring(1,QNA_IMG_DIR.indexOf(".metadata"));
			QNA_IMG_DIR=QNA_IMG_DIR.replace("/", "\\");
			QNA_IMG_DIR+="nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\qnaImages\\temp\\";

			if(imageName!=null && imageName.length !=0) {
				for(String imgName:imageName) {
					File srcFile=new File(QNA_IMG_DIR+imgName);
					srcFile.delete();
				}	
			}
			
		} catch (Exception e) {
			logger.info("이미지 삭제 중 에러");
			e.printStackTrace();
		}
	}
	

}
