package com.springmvc.nemo.user.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface SignUpDAO {
	
	public void join(UserVO userVO) throws DataAccessException;
	
	public void interests(List<InterestsVO> interestsList) throws DataAccessException;

}
