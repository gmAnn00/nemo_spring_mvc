package com.springmvc.nemo.user.dao;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.KakaoVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface LoginDAO {
	
	public Boolean loginTry(UserVO userVO) throws DataAccessException;
	
	public UserVO findUserById(String user_id) throws DataAccessException;
	
	public String findId(UserVO userVO) throws DataAccessException;
	
	public Boolean resetPasswordCheck(UserVO userVO) throws DataAccessException;
	
	public void resetPassword(UserVO userVO) throws DataAccessException;

	public boolean isAlreayKakaoUser(KakaoVO kakaoVO) throws DataAccessException;

	public void updateKakaoUser(KakaoVO kakaoVO) throws DataAccessException;

	public void insertKakaoUser(KakaoVO kakaoVO) throws DataAccessException;

	public void addUserTbl(UserVO userVO) throws DataAccessException;

	public UserVO getUserInfo(String user_id) throws DataAccessException;

	public String getAccessToken(String user_id) throws DataAccessException;

	public void delAccessToken(String user_id) throws DataAccessException;

}
