package com.springmvc.nemo.user.service;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.KakaoVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface LoginService {

	public Boolean loginTry(UserVO userVO) throws DataAccessException;

	public UserVO findUserById(String user_id) throws DataAccessException;

	public String findId(UserVO userVO) throws DataAccessException;

	public Boolean resetPasswordCheck(UserVO userVO) throws DataAccessException;

	public void resetPassword(UserVO userVO) throws DataAccessException;

	public String getKakaoAccessToken(String code) throws Exception;
	
	public KakaoVO getKakaoUserInfo(String accessToken) throws Exception;

	public UserVO kakaoLogin(KakaoVO kakaoVO) throws Exception;

	public void kakaoLogout(String user_id) throws Exception;

}
