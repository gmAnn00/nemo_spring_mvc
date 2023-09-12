package com.springmvc.nemo.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.group.vo.WaitListVO;
import com.springmvc.nemo.member.dao.MemberDAO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public UserVO getUserInfo(String user_id) throws DataAccessException {
		
		return memberDAO.getUserInfo(user_id);
	}
	
	@Override
	public List<UserVO> getMemberInfo(JoinVO joinVO) throws DataAccessException {
		
		return memberDAO.getMemberInfo(joinVO);
	}
	
	@Override
	public boolean isAlreadyCancel(JoinVO joinVO) throws DataAccessException {
		
		return memberDAO.isAlreadyCancel(joinVO);
	}
	
	@Override
	public void cancelGroup(JoinVO joinVO) throws DataAccessException {
		
		 memberDAO.cancelGroup(joinVO);
	}
	
	@Override
	public void cancelWait(WaitListVO waitListVO) throws DataAccessException {
		
		memberDAO.cancelWait(waitListVO);
	}

}
