package com.springmvc.nemo.user.service;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.UserVO;

public interface SignUpService {
	
	public void join(UserVO userVO) throws DataAccessException;

}
