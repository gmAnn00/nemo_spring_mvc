package com.springmvc.nemo.schedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.schedule.vo.AttendVO;
import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.schedule.vo.UsingScheduleVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface ScheduleDAO {

	public List<UsingScheduleVO> getCommingSchedule(int group_id) throws DataAccessException;

	public List<String> getScheduleDate(Map<String, Object> scheduleMap) throws DataAccessException;

	public boolean isScheduleExist(Map<String, Object> scheduleMap) throws DataAccessException;

	public UsingScheduleVO getSchedule(Map<String, Object> scheduleMap) throws DataAccessException;

	public boolean isAttend(Map<String, Object> scheduleMap) throws DataAccessException;

	public List<UserVO> getAttendUsersList(Map<String, Object> scheduleMap) throws DataAccessException;
	
	public int getNewScheduleId() throws DataAccessException;
	
	public int getNewAttendId() throws DataAccessException;

	public void addSchedule(ScheduleVO schedule) throws DataAccessException;
	
	public void attendSchedule(AttendVO attendVO) throws DataAccessException;

	public void modSchedule(ScheduleVO schedule) throws DataAccessException;

	public String getScheduleMakerId(int schedule_id) throws DataAccessException;

	public void delAttend(int schedule_id) throws DataAccessException;

	public void delSchedule(int schedule_id) throws DataAccessException;

	public boolean isAttendById(AttendVO attendVO) throws DataAccessException;

	public void cancelSchedule(AttendVO attendVO) throws DataAccessException;

}
