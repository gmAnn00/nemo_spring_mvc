package com.springmvc.nemo.group.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.schedule.vo.ScheduleVO;
import com.springmvc.nemo.user.vo.BookmarkVO;
import com.springmvc.nemo.user.vo.UserVO;

@Repository("groupDAO")
public class GroupDAOImpl implements GroupDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getNewGroupId() throws DataAccessException {
		return sqlSession.selectOne("mapper.group.getNewGroupId");
	}
	
	@Override
	public void createGroup(Map<String, Object> groupMap) throws DataAccessException {
		
		sqlSession.insert("mapper.group.createGroup", groupMap);
	}
	
	@Override
	public void joinGroup(JoinVO joinVO) throws DataAccessException {
		sqlSession.insert("mapper.group.joinGroup", joinVO);
		sqlSession.update("mapper.group.increaseCurrentMemNo", joinVO.getGroup_id());
	}
	
	@Override
	public GroupVO getGroupInfo(int group_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.group.getGroupInfo", group_id);
	}
	
	@Override
	public UserVO getGroupLeaderInfo(int group_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.group.getGroupLeaderInfo", group_id);
	}
	
	@Override
	public Date getRecentDate(int group_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.group.getRecentDate", group_id);
	}
	
	@Override
	public boolean isBookmark(BookmarkVO bookmarkVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.group.isBookmark", bookmarkVO);
	}
	
	@Override
	public String addBookmark(BookmarkVO bookmarkVO) throws DataAccessException {
		sqlSession.insert("mapper.group.addBookmark", bookmarkVO);
		sqlSession.update("mapper.group.increaseBookmarkNo", bookmarkVO);
		return "true";
	}
	
	@Override
	public String delBookmark(BookmarkVO bookmarkVO) throws DataAccessException {
		sqlSession.delete("mapper.group.delBookmark", bookmarkVO);
		sqlSession.update("mapper.group.decreaseBookmarkNo", bookmarkVO);
		return "false";
	}
	
	@Override
	public boolean isGroupMember(JoinVO joinVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.group.isGroupMember", joinVO);
	}
	
	@Override
	public List<ScheduleVO> getPreviewSchedule(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.group.getPreviewSchedule", group_id);
	}
	
	@Override
	public List<BoardVO> getPreviewBoard(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.group.getPreviewBoard", group_id);
	}
	
	@Override
	public List<UserVO> getGroupMember(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.group.getGroupMember", group_id);
	}


}
