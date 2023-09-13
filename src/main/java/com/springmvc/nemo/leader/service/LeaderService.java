package com.springmvc.nemo.leader.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.UserVO;

public interface LeaderService {

	public UserVO getUserInfo(String user_id) throws DataAccessException;

	public List<UserVO> getMemberInfo(int group_id) throws DataAccessException;
	
	public boolean isGroupMember(Map<String, Object> mandateMap) throws DataAccessException;

	public String mandateLeader(Map<String, Object> mandateMap) throws DataAccessException;

	public String exileMember(Map<String, Object> exileMap) throws DataAccessException;

	
}
