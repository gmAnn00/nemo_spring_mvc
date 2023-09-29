package com.springmvc.nemo.qna.controller;

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

import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.qna.service.QnaService;
import com.springmvc.nemo.qna.vo.QnaVO;

@Controller("qnaController")
public class QnaControllerImpl implements QnaController{
	
	private static final Logger logger = LoggerFactory.getLogger(QnaControllerImpl.class);
	
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
		
		if(admin == 0 && !user_id.equals(qna.getUser_id())) {
			mav.setViewName("message");
			mav.addObject("data", new Message("해당 문의사항을 볼 수 없습니다.", request.getContextPath() + "/qna"));
			
			return mav;
		}
		
		
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		mav.addObject("qna", qna);
		
		return mav;
	}

}
