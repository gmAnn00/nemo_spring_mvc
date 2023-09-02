package com.springmvc.nemo.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.mypage.vo.ModInfoVO;
import com.springmvc.nemo.mypage.vo.MyProfileVO;
import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface MyPageDAO {
	
	public MyProfileVO findMyProfileById(String user_id) throws DataAccessException;
	
	public List<InterestsVO> findMyInterestsById(String user_id) throws DataAccessException;
	
	public ModInfoVO findMyUserInfoById(String user_id) throws DataAccessException;
	
	public void modProfile(Map<String, Object> userMap) throws DataAccessException;

	public void modImage(Map<String, Object> userMap) throws DataAccessException;
	
	public void deleteInterests(String user_Id) throws DataAccessException;

	public void addInterests(List<InterestsVO> interestsList) throws DataAccessException;

	public boolean checkPassword(UserVO userVO) throws DataAccessException;

	public void delUser(UserVO userVO) throws DataAccessException;

}
