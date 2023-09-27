package com.springmvc.nemo.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.mypage.vo.CommingScheduleVO;
import com.springmvc.nemo.mypage.vo.ModInfoVO;
import com.springmvc.nemo.mypage.vo.MyProfileVO;
import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface MyPageService {
	
	public MyProfileVO findMyProfileById(String user_id) throws DataAccessException;
	
	public List<InterestsVO> findMyInterestsById(String user_id) throws DataAccessException;

	public ModInfoVO findMyUserInfoById(String user_id) throws DataAccessException;
	
	public void modProfile(Map<String, Object> userMap) throws DataAccessException;

	public void modImage(Map<String, Object> userMap) throws DataAccessException;

	public void modInterests(List<InterestsVO> interestsList) throws DataAccessException;

	public boolean delUser(UserVO userVO) throws DataAccessException;

	public List<CommingScheduleVO> getCommingSchedules(String user_id) throws DataAccessException;

	public List<String> getSelectYMSchedule(Map<String, String> scheduleMap) throws DataAccessException;

	public List<CommingScheduleVO> getSelectMonthSchedule(Map<String, String> scheduleMap) throws DataAccessException;

	public List<GroupVO> getLeaderGroup(String user_id) throws DataAccessException;

	public List<GroupVO> getGroup(String user_id) throws DataAccessException;
	
	public List<GroupVO> getWaitGroup(String user_id) throws DataAccessException;

	public List<GroupVO> getBookmarkGroup(String user_id) throws DataAccessException;

	public List<BoardVO> getMyBoardList(String user_id) throws DataAccessException;

	public List<CommentVO> getMyCommentList(String user_id) throws DataAccessException;
	
	

	
	
}
