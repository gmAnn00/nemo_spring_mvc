package com.springmvc.nemo.schedule.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.common.SqlTimestampPropertyEditor;
import com.springmvc.nemo.schedule.service.ScheduleService;
import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.schedule.vo.UsingScheduleVO;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("scheduleController")
public class ScheduleControllerImpl implements ScheduleController{
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleControllerImpl.class);
	
	@Autowired
	private ScheduleService scheduleService;
	
	/** 어노테이션을 이용한 방법 **/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		//binder.registerCustomEditor(Timestamp.class, new SqlTimestampPropertyEditor(dateFormat));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}  
	
	@RequestMapping(value = "/group/schedule", method = RequestMethod.GET)
	@Override
	public ModelAndView schedule(
			@RequestParam("group_id") String str_group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		viewName += "/schedule";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		// TODO
		// 다가오는 일정 정보 보내기
		int group_id = Integer.parseInt(str_group_id);
		List<UsingScheduleVO> commingScheduleList = scheduleService.getCommingSchedule(group_id);
		mav.addObject("commingScheduleList", commingScheduleList);

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/group/schedule/ajaxcalendar", method = RequestMethod.GET, produces="application/text;charset=utf-8")
	@Override
	public String ajaxCalendar(
			@RequestParam("group_id") String str_group_id,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		int group_id = Integer.parseInt(str_group_id);
		String currentYM = year + "/" + month;
		Map<String, Object> scheduleMap = new HashMap();
		scheduleMap.put("group_id", group_id);
		scheduleMap.put("currentYM", currentYM);
		
		List<String> scheduleDateList = new ArrayList();
		scheduleDateList = scheduleService.getScheduleDate(scheduleMap);
		Gson gson = new Gson();
		String scheduleDateJson = gson.toJson(scheduleDateList);
		
		return scheduleDateJson;
	}
	
	@ResponseBody
	@RequestMapping(value = "/group/schedule/checkschedule", method = RequestMethod.GET, produces="application/text;charset=utf-8")
	@Override
	public String checkSchedule(
			@RequestParam("group_id") String str_group_id,
			@RequestParam("selScheDate") String selScheDate,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		int group_id = Integer.parseInt(str_group_id);
		Map<String, Object> scheduleMap = new HashMap();
		scheduleMap.put("user_id", user_id);
		scheduleMap.put("group_id", group_id);
		scheduleMap.put("selScheDate", selScheDate);
		
		// true : 일정 있음, false : 일정 없음
		boolean isScheduleExistResult = scheduleService.isScheduleExist(scheduleMap);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("isScheduleExist", isScheduleExistResult);
		
		if(isScheduleExistResult) {
			
			UsingScheduleVO usingScheduleVO = scheduleService.getSchedule(scheduleMap);
			boolean isAttendResult = scheduleService.isAttend(scheduleMap);
			List<UserVO> attendUserList = new ArrayList<UserVO>();
			attendUserList = scheduleService.getAttendUsersList(scheduleMap);
			
			jsonObject.addProperty("usingScheduleVO", gson.toJson(usingScheduleVO));
			jsonObject.addProperty("isAttend", isAttendResult);
			jsonObject.addProperty("attendUsersList", gson.toJson(attendUserList));
		}
		
		
		String scheduleInfo = gson.toJson(jsonObject);
		//logger.info("scheduleInfo={}",scheduleInfo);
		return scheduleInfo;
	}
	
	@RequestMapping(value = "/group/schedule/addschedule", method = RequestMethod.POST)
	@Override
	public ModelAndView addSchedule(@RequestParam("group_id") String str_group_id,
			@ModelAttribute("scheduleVO") ScheduleVO scheduleVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("scheduleVO={}",scheduleVO.toString());
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		int group_id = Integer.parseInt(str_group_id);
		
		ScheduleVO schedule = scheduleVO;
		schedule.setGroup_id(group_id);
		schedule.setUser_id(user_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		// true : 같은 날에 일정이 이미 존재함, false: 일정이 존재하지 않음
		boolean alreadyExist=false;
		
		
		if(schedule.getLocation().equals("") || schedule.getLocation().length() == 0) {
			mav.addObject("data", new Message("장소를 선택해 주세요", request.getContextPath()+"/group/schedule?group_id="+str_group_id));
			return mav;
		}else if(alreadyExist) {
			mav.addObject("data", new Message("같은 날이 일정이 이미 존재합니다.", request.getContextPath()+"/group/schedule?group_id="+str_group_id));
			return mav;
		}
		
		mav.addObject("data", new Message("새 일정이 등록되었습니다.", request.getContextPath()+"/group/schedule?group_id="+str_group_id));
		
		return mav;
	}
	

}
