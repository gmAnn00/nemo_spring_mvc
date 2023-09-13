package com.springmvc.nemo.leader.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.user.vo.UserVO;

@Repository("leaderDAO")
public class LeaderDAOImpl implements LeaderDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public UserVO getUserInfo(String user_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.leader.getUserInfo", user_id);
	}
	
	@Override
	public List<UserVO> getMemberInfo(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.leader.getMemberInfo", group_id);
	}
	
	@Override
	public List<UserVO> getWaitUserInfo(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.leader.getWaitUserInfo", group_id);
	}
	
	@Override
	public boolean isGroupMember(Map<String, Object> map) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.leader.isGroupMember", map);
	}
	
	@Override
	public String getUserNickname(String target_id) throws DataAccessException{
		return sqlSession.selectOne("mapper.leader.getUserNickname", target_id);
	}
	
	@Override
	public void mandateLeader(Map<String, Object> mandateMap) throws DataAccessException {
		
		sqlSession.update("mapper.leader.mandateLeader", mandateMap);
	}
	
	@Override
	public void exileMember(Map<String, Object> exileMap) throws DataAccessException {
		
		sqlSession.update("mapper.leader.exileMember", exileMap);
		sqlSession.update("mapper.leader.decreaseCurrentMemNo", exileMap);
	}
	
	
	@Override
	public boolean isWait(Map<String, Object> map) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.leader.isWait", map);
	}
	
	@Override
	public void approveMember(Map<String, Object> approveMap) throws DataAccessException {
		
		int getCancelResult = sqlSession.selectOne("mapper.leader.getCancel", approveMap);
		
		if(getCancelResult == 0) {
			
			sqlSession.insert("mapper.leader.addJoin", approveMap);
			
		} else if(getCancelResult == 1) {
	
			sqlSession.update("mapper.leader.rejoin", approveMap);
			
		} else {
			return;
		}

		sqlSession.update("mapper.leader.increaseCurrentMemNo", approveMap);
		sqlSession.delete("mapper.leader.deleteWait", approveMap);
	}
	
	
	@Override
	public void rejectMember(Map<String, Object> rejectMap) throws DataAccessException {
		
		sqlSession.delete("mapper.leader.deleteWait", rejectMap);
	}

}
