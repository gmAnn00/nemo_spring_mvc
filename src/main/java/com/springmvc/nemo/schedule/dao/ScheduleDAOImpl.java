package com.springmvc.nemo.schedule.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.schedule.vo.AttendVO;
import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.schedule.vo.UsingScheduleVO;
import com.springmvc.nemo.user.vo.UserVO;

@Repository("scheduleDAO")
public class ScheduleDAOImpl implements ScheduleDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<UsingScheduleVO> getCommingSchedule(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.schedule.getCommingSchedule", group_id);
	}
	
	@Override
	public List<String> getScheduleDate(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.schedule.getScheduleDate", scheduleMap);
	}
	
	@Override
	public boolean isScheduleExist(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.schedule.isScheduleExist", scheduleMap);
	}
	
	@Override
	public UsingScheduleVO getSchedule(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.schedule.getSchedule", scheduleMap);
	}
	
	@Override
	public boolean isAttend(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.schedule.isAttend", scheduleMap);
	}
	
	@Override
	public List<UserVO> getAttendUsersList(Map<String, Object> scheduleMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.schedule.getAttendUsersList", scheduleMap);
	}
	
	@Override
	public int getNewScheduleId() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.schedule.getNewScheduleId");
	}
	
	@Override
	public int getNewAttendId() throws DataAccessException {
		return sqlSession.selectOne("mapper.schedule.getNewAttendId");
	}
	
	@Override
	public void addSchedule(ScheduleVO schedule) throws DataAccessException {
		
		sqlSession.insert("mapper.schedule.addSchedule", schedule);
		
	}
	
	@Override
	public void attendSchedule(AttendVO attendVO) throws DataAccessException {
		int schedule_id = attendVO.getSchedule_id();
		sqlSession.update("mapper.schedule.increaseAttendeeNo", schedule_id);
		sqlSession.insert("mapper.schedule.attendSchedule", attendVO);
	}
}
