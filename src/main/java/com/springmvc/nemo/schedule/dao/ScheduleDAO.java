package com.springmvc.nemo.schedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.schedule.vo.UsingScheduleVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface ScheduleDAO {

	public List<UsingScheduleVO> getCommingSchedule(int group_id) throws DataAccessException;

	public List<String> getScheduleDate(Map<String, Object> scheduleMap) throws DataAccessException;

	public boolean isScheduleExist(Map<String, Object> scheduleMap) throws DataAccessException;

	public UsingScheduleVO getSchedule(Map<String, Object> scheduleMap) throws DataAccessException;

	public boolean isAttend(Map<String, Object> scheduleMap) throws DataAccessException;

	public List<UserVO> getAttendUsersList(Map<String, Object> scheduleMap) throws DataAccessException;

}
