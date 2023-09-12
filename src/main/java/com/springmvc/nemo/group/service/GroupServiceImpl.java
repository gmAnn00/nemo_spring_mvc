package com.springmvc.nemo.group.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.group.dao.GroupDAO;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.group.vo.WaitListVO;
import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.user.vo.BookmarkVO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("groupService")
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Override
	public int createGroup(Map<String, Object> groupMap) throws DataAccessException {
		int group_id = groupDAO.getNewGroupId();
		Map<String, Object> newGroupMap = groupMap;
		newGroupMap.put("group_id", group_id);
		
		groupDAO.createGroup(newGroupMap);
		
		JoinVO joinVO = new JoinVO();
		joinVO.setGroup_id(group_id);
		joinVO.setUser_id((String)newGroupMap.get("group_leader"));
		groupDAO.joinGroup(joinVO);
		
		return group_id;
	}
	
	@Override
	public GroupVO getGroupInfo(int group_id) throws DataAccessException {

		return groupDAO.getGroupInfo(group_id);
	}
	
	@Override
	public UserVO getGroupLeaderInfo(int group_id) throws DataAccessException {
		
		return groupDAO.getGroupLeaderInfo(group_id);
	}
	
	@Override
	public Date getRecentDate(int group_id) throws DataAccessException {
		
		return groupDAO.getRecentDate(group_id);
	}
	
	@Override
	public boolean isBookmark(BookmarkVO bookmarkVO) throws DataAccessException {
		
		return groupDAO.isBookmark(bookmarkVO);
	}
	
	@Override
	public String toggleBookmark(BookmarkVO bookmarkVO) throws DataAccessException {
		boolean isBMResult = groupDAO.isBookmark(bookmarkVO);
		String BMResult = "false";
		if(isBMResult) {
			BMResult = groupDAO.delBookmark(bookmarkVO);
		} else {
			BMResult = groupDAO.addBookmark(bookmarkVO);
		}
		return BMResult;
	}
	
	@Override
	public boolean isGroupMember(JoinVO joinVO) throws DataAccessException {
		
		return groupDAO.isGroupMember(joinVO);
	}
	
	@Override
	public boolean isFullGroup(int group_id) throws DataAccessException {
		
		GroupVO groupVO = groupDAO.getGroupInfo(group_id);
		int maxNum = groupVO.getMax_memno();
		int curNum = groupVO.getCurrent_memno();
		
		if(maxNum == curNum) {
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public boolean isWaitGroup(int group_id) throws DataAccessException {
		
		return groupDAO.isWaitGroup(group_id);
	}
	
	@Override
	public boolean isAlreadyWait(WaitListVO waitListVO) throws DataAccessException {
		
		return groupDAO.isAlreadyWait(waitListVO);
	}
	
	@Override
	public void waitGroup(WaitListVO waitListVO) throws DataAccessException {
		
		groupDAO.waitGroup(waitListVO);
	}
	
	
	@Override
	public void joinGroup(JoinVO joinVO) throws DataAccessException {
		groupDAO.joinGroup(joinVO);
		
	}
	
	@Override
	public List<ScheduleVO> getPreviewSchedule(int group_id) throws DataAccessException {
		
		return groupDAO.getPreviewSchedule(group_id);
	}
	
	@Override
	public List<BoardVO> getPreviewBoard(int group_id) throws DataAccessException {
		
		return groupDAO.getPreviewBoard(group_id);
	}
	
	
	@Override
	public List<UserVO> getGroupMember(int group_id) throws DataAccessException {
		
		return groupDAO.getGroupMember(group_id);
	}
	
}
