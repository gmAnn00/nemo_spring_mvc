package com.springmvc.nemo.leader.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.group.vo.GroupVO;
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
	public List<UserVO> getWaitUserInfo(int group_id) throws DataAccessException {
		
		return leaderDAO.getWaitUserInfo(group_id);
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
	
	
	@Override
	public boolean isWait(Map<String, Object> map) throws DataAccessException {
		
		return leaderDAO.isWait(map);
	}
	
	
	@Override
	public String approveMember(Map<String, Object> approveMap) throws DataAccessException {
		
		leaderDAO.approveMember(approveMap);
		
		String target_id = (String) approveMap.get("target_id");
		String nickname = leaderDAO.getUserNickname(target_id);
		
		return nickname;
		
	}
	
	
	@Override
	public String rejectMember(Map<String, Object> rejectMap) throws DataAccessException {
		
		leaderDAO.rejectMember(rejectMap);
		
		String target_id = (String) rejectMap.get("target_id");
		String nickname = leaderDAO.getUserNickname(target_id);
		
		return nickname;
	}
	
	
	@Override
	public GroupVO getGroupInfo(int group_id) throws DataAccessException {
		
		return leaderDAO.getGroupInfo(group_id);
	}
	
	
	@Override
	public int getCurrentMaxMemNo(int group_id) throws DataAccessException {
		
		return leaderDAO.getCurrentMaxMemNo(group_id);
	}
	
	@Override
	public void modGroupSetting(Map<String, Object> groupMap) throws DataAccessException {
		
		leaderDAO.modGroupSetting(groupMap);
		
	}
	
	
}
