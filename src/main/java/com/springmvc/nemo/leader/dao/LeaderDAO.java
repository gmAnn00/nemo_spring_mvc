package com.springmvc.nemo.leader.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.UserVO;

public interface LeaderDAO {

	public UserVO getUserInfo(String user_id) throws DataAccessException;

	public List<UserVO> getMemberInfo(int group_id) throws DataAccessException;
	
	public boolean isGroupMember(Map<String, Object> map) throws DataAccessException;

	public void mandateLeader(Map<String, Object> mandateMap) throws DataAccessException;

	public String getUserNickname(String target_id) throws DataAccessException;

	public void exileMember(Map<String, Object> exileMap) throws DataAccessException;

	

}
