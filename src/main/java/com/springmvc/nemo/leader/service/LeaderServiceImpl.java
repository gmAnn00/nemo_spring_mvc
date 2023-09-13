package com.springmvc.nemo.leader.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.leader.dao.LeaderDAO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("leaderService")
public class LeaderServiceImpl implements LeaderService{
	
	@Autowired
	private LeaderDAO leaderDAO;
	
	@Override
	public UserVO getUserInfo(String user_id) throws DataAccessException {
		
		return leaderDAO.getUserInfo(user_id);
	}
	
	@Override
	public List<UserVO> getMemberInfo(int group_id) throws DataAccessException {
		
		return leaderDAO.getMemberInfo(group_id);
	}
	
	@Override
	public boolean isGroupMember(Map<String, Object> map) throws DataAccessException {
		
		return leaderDAO.isGroupMember(map);
	}
	
	
	@Override
	public String mandateLeader(Map<String, Object> mandateMap) throws DataAccessException {
		
		leaderDAO.mandateLeader(mandateMap);
		
		String target_id = (String) mandateMap.get("target_id");
		String nickname = leaderDAO.getUserNickname(target_id);
		
		return nickname;
	}
	
	@Override
	public String exileMember(Map<String, Object> exileMap) throws DataAccessException {
		
		leaderDAO.exileMember(exileMap);
		
		String target_id = (String) exileMap.get("target_id");
		String nickname = leaderDAO.getUserNickname(target_id);
		
		return nickname;
	}
}
