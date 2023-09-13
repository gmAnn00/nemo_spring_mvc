package com.springmvc.nemo.leader.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.UserVO;

public interface LeaderDAO {

	public UserVO getUserInfo(String user_id) throws DataAccessException;

	public List<UserVO> getMemberInfo(int group_id) throws DataAccessException;

}
