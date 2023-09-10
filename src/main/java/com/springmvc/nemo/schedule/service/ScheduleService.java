package com.springmvc.nemo.schedule.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.schedule.vo.UsingScheduleVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface ScheduleService {

	public List<UsingScheduleVO> getCommingSchedule(int group_id) throws DataAccessException;

	public List<String> getScheduleDate(Map<String, Object> scheduleMap) throws DataAccessException;

	public boolean isScheduleExist(Map<String, Object> scheduleMap) throws DataAccessException;

	public UsingScheduleVO getSchedule(Map<String, Object> scheduleMap) throws DataAccessException;

	public boolean isAttend(Map<String, Object> scheduleMap) throws DataAccessException;

	public List<UserVO> getAttendUsersList(Map<String, Object> scheduleMap) throws DataAccessException;

	public void addSchedule(ScheduleVO schedule) throws DataAccessException;

	public boolean modSchedule(ScheduleVO schedule) throws DataAccessException;

	public String getScheduleMakerId(int schedule_id) throws DataAccessException;

	public void delSchedule(int schedule_id) throws DataAccessException;

}
