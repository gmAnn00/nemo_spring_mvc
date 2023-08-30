package com.springmvc.nemo.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.mypage.vo.ModInfoVO;
import com.springmvc.nemo.mypage.vo.MyProfileVO;
import com.springmvc.nemo.user.vo.InterestsVO;

public interface MyPageService {
	
	public MyProfileVO findMyProfileById(String user_id) throws DataAccessException;
	
	public List<InterestsVO> findMyInterestsById(String user_id) throws DataAccessException;

	public ModInfoVO findMyUserInfoById(String user_id) throws DataAccessException;
	
	public void modProfile(Map userMap) throws DataAccessException;
	
}
