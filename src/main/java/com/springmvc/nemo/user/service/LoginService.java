package com.springmvc.nemo.user.service;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.UserVO;

public interface LoginService {

	public Boolean loginTry(UserVO userVO) throws DataAccessException;

	public UserVO findUserById(String user_id) throws DataAccessException;

	public String findId(UserVO userVO) throws DataAccessException;

	public Boolean resetPasswordCheck(UserVO userVO) throws DataAccessException;

	public void resetPassword(UserVO userVO) throws DataAccessException;

}
