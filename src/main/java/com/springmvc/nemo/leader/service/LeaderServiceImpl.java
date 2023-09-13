package com.springmvc.nemo.leader.service;

import java.util.List;

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
}
