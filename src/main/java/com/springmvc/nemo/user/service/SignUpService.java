package com.springmvc.nemo.user.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface SignUpService {
	
	public void join(UserVO userVO) throws DataAccessException;
	
	public void interests(List<InterestsVO> interestsList) throws DataAccessException;

}
