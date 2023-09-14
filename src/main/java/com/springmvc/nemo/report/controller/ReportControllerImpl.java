package com.springmvc.nemo.report.controller;

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
import com.springmvc.nemo.report.service.ReportService;
import com.springmvc.nemo.report.vo.GroupReportVO;
import com.springmvc.nemo.report.vo.UserReportVO;

@Controller("reportController")
public class ReportControllerImpl implements ReportController{
	
	private static final Logger logger = LoggerFactory.getLogger(ReportControllerImpl.class);
	
	@Autowired
	private ReportService reportService;
	
	
	@RequestMapping(value = "/report/group", method = RequestMethod.GET)
	@Override
	public ModelAndView reportGroup(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		GroupReportVO groupReportVO = new GroupReportVO();
		groupReportVO.setReporter_id(user_id);
		groupReportVO.setGroup_id(group_id);
		
		// true : 성공, false : 실패(이미 신고한 소모임)
		boolean reportResult = reportService.reportGroup(groupReportVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		if(reportResult) {
			mav.addObject("data", new Message("소모임을 신고하였습니다.", request.getContextPath()+"/group/groupmain?group_id="+group_id));
		}else {
			mav.addObject("data", new Message("이미 신고한 소모임입니다.", request.getContextPath()+"/group/groupmain?group_id="+group_id));
		}
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/report/member", method = RequestMethod.GET)
	@Override
	public ModelAndView reportMember(
			@RequestParam("group_id") int group_id,
			@RequestParam("accused_id") String accused_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		UserReportVO userReportVO = new UserReportVO();
		userReportVO.setReporter_id(user_id);
		userReportVO.setAccused_id(accused_id);
		
		// true : 성공, false : 실패(이미 신고한 소모임)
		boolean reportResult = reportService.reportMember(userReportVO);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");

		if(reportResult) {
			mav.addObject("data", new Message("멤버를 신고하였습니다.", request.getContextPath()+"/group/groupmain?group_id="+group_id));
		}else {
			mav.addObject("data", new Message("이미 신고한 멤버입니다.", request.getContextPath()+"/group/groupmain?group_id="+group_id));
		}
		
		
		
		return mav;
	}

}
