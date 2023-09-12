package com.springmvc.nemo.group.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.group.vo.WaitListVO;
import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.user.vo.BookmarkVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface GroupService {

	public int createGroup(Map<String, Object> groupMap) throws DataAccessException;

	public GroupVO getGroupInfo(int group_id) throws DataAccessException;

	public UserVO getGroupLeaderInfo(int group_id) throws DataAccessException;

	public Date getRecentDate(int group_id) throws DataAccessException;

	public boolean isBookmark(BookmarkVO bookmarkVO) throws DataAccessException;

	public String toggleBookmark(BookmarkVO bookmarkVO) throws DataAccessException;

	public boolean isGroupMember(JoinVO joinVO) throws DataAccessException;
	
	public boolean isWaitGroup(int group_id) throws DataAccessException;

	public boolean isAlreadyWait(WaitListVO waitListVO) throws DataAccessException;

	public void waitGroup(WaitListVO waitListVO) throws DataAccessException;

	public boolean joinGroup(JoinVO joinVO) throws DataAccessException;

	public boolean isFullGroup(int group_id) throws DataAccessException;

	public List<ScheduleVO> getPreviewSchedule(int group_id) throws DataAccessException;

	public List<BoardVO> getPreviewBoard(int group_id) throws DataAccessException;

	public List<UserVO> getGroupMember(int group_id) throws DataAccessException;

	



}
