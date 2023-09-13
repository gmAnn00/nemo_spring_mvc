package com.springmvc.nemo.leader.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface LeaderDAO {

	public UserVO getUserInfo(String user_id) throws DataAccessException;

	public List<UserVO> getMemberInfo(int group_id) throws DataAccessException;
	
	public List<UserVO> getWaitUserInfo(int group_id) throws DataAccessException;
	
	public boolean isGroupMember(Map<String, Object> map) throws DataAccessException;

	public void mandateLeader(Map<String, Object> mandateMap) throws DataAccessException;

	public String getUserNickname(String target_id) throws DataAccessException;

	public void exileMember(Map<String, Object> exileMap) throws DataAccessException;

	public boolean isWait(Map<String, Object> map) throws DataAccessException;

	public void approveMember(Map<String, Object> approveMap) throws DataAccessException;

	public void rejectMember(Map<String, Object> rejectMap) throws DataAccessException;

	public GroupVO getGroupInfo(int group_id) throws DataAccessException;
	
	public int getCurrentMaxMemNo(int group_id) throws DataAccessException;

	public void modGroupSetting(Map<String, Object> groupMap) throws DataAccessException;

}
