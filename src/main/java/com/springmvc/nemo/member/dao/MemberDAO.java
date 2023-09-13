package com.springmvc.nemo.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.group.vo.WaitListVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface MemberDAO {

	public UserVO getUserInfo(String user_id) throws DataAccessException;

	public List<UserVO> getMemberInfo(JoinVO joinVO) throws DataAccessException;

	public boolean isAlreadyCancel(JoinVO joinVO) throws DataAccessException;

	public void cancelGroup(JoinVO joinVO) throws DataAccessException;

	public void cancelWait(WaitListVO waitListVO) throws DataAccessException;

	public String getGroupLeader(int group_id) throws DataAccessException;

}
