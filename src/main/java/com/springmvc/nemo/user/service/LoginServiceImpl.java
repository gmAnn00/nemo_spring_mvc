package com.springmvc.nemo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.user.dao.LoginDAO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public Boolean loginTry(UserVO userVO) throws DataAccessException {
		return loginDAO.loginTry(userVO);
	}

	@Override
	public UserVO findUserById(String user_id) throws DataAccessException {
		return loginDAO.findUserById(user_id);
	}

	@Override
	public String findId(UserVO userVO) throws DataAccessException {
		
		return loginDAO.findId(userVO);
	}

	@Override
	public Boolean resetPasswordCheck(UserVO userVO) throws DataAccessException {
		
		return loginDAO.resetPasswordCheck(userVO);
	}

	@Override
	public void resetPassword(UserVO userVO) throws DataAccessException {
		
		loginDAO.resetPassword(userVO);
		
	}
	
	
	

}
