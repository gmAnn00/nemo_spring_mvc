package com.springmvc.nemo.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.mypage.dao.MyPageDAO;
import com.springmvc.nemo.mypage.vo.ModInfoVO;
import com.springmvc.nemo.mypage.vo.MyProfileVO;
import com.springmvc.nemo.user.vo.InterestsVO;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	private MyPageDAO myPageDAO;
	
	@Override
	public MyProfileVO findMyProfileById(String user_id) throws DataAccessException {
		
		return myPageDAO.findMyProfileById(user_id);
	}
	
	@Override
	public List<InterestsVO> findMyInterestsById(String user_id) throws DataAccessException {
		
		return myPageDAO.findMyInterestsById(user_id);
	}
	
	@Override
	public ModInfoVO findMyUserInfoById(String user_id) throws DataAccessException {
		
		return myPageDAO.findMyUserInfoById(user_id);
	}
	
	@Override
	public void modProfile(Map userMap) throws DataAccessException {
		myPageDAO.modProfile(userMap);
		
	}

}