package com.springmvc.nemo.schedule.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.schedule.dao.ScheduleDAO;
import com.springmvc.nemo.schedule.vo.AttendVO;
import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.schedule.vo.UsingScheduleVO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	private ScheduleDAO scheduleDAO;
	
	@Override
	public List<UsingScheduleVO> getCommingSchedule(int group_id) throws DataAccessException {
		
		return scheduleDAO.getCommingSchedule(group_id);
	}
	
	@Override
	public List<String> getScheduleDate(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return scheduleDAO.getScheduleDate(scheduleMap);
	}
	
	@Override
	public boolean isScheduleExist(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return scheduleDAO.isScheduleExist(scheduleMap);
	}
	
	@Override
	public UsingScheduleVO getSchedule(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return scheduleDAO.getSchedule(scheduleMap);
	}
	
	@Override
	public boolean isAttend(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return scheduleDAO.isAttend(scheduleMap);
	}
	
	@Override
	public List<UserVO> getAttendUsersList(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return scheduleDAO.getAttendUsersList(scheduleMap);
	}
	
	@Override
	public void addSchedule(ScheduleVO schedule) throws DataAccessException {
		ScheduleVO scheduleVO = schedule;
		int schedule_id = scheduleDAO.getNewScheduleId();
		int attend_id = scheduleDAO.getNewAttendId();
		
		scheduleVO.setSchedule_id(schedule_id);
		
		AttendVO attendVO = new AttendVO();
		attendVO.setAttend_id(attend_id);
		attendVO.setSchedule_id(schedule_id);
		attendVO.setUser_id(scheduleVO.getUser_id());
		
		scheduleDAO.addSchedule(scheduleVO);
		scheduleDAO.attendSchedule(attendVO);
	}

}
