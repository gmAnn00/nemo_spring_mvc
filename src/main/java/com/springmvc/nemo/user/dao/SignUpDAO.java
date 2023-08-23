package com.springmvc.nemo.user.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.user.vo.UserVO;

public interface SignUpDAO {
	
	public void join(UserVO userVO) throws DataAccessException;

}
