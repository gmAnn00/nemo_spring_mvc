package com.springmvc.nemo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.nemo.user.dao.SignUpDAO;
import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("signUpService")
@Transactional(propagation = Propagation.REQUIRED)
public class SignUpServiceImpl implements SignUpService{
	
	@Autowired
	private SignUpDAO signUpDAO;
	
	
	@Override
	public void join(UserVO userVO) throws DataAccessException {
		signUpDAO.join(userVO);
		
	}


	@Override
	public void interests(List<InterestsVO> interestsList) throws DataAccessException {
		signUpDAO.interests(interestsList);
		
	}
	
	

}
