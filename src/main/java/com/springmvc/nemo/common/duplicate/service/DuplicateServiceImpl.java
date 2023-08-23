package com.springmvc.nemo.common.duplicate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.common.duplicate.dao.DuplicateDAO;

@Service("duplicateService")
public class DuplicateServiceImpl implements DuplicateService{
	
	@Autowired
	private DuplicateDAO duplicateDAO;

	@Override
	public String idCheck(String user_id) throws DataAccessException {
//		System.out.println("duplicate Service");
		return duplicateDAO.idCheck(user_id);
	}

	@Override
	public String nicknameCheck(String nickname) throws DataAccessException {
		
		return duplicateDAO.nicknameCheck(nickname);
	}

	@Override
	public String emailCheck(String email) throws DataAccessException {
		
		return duplicateDAO.emailCheck(email);
	}
	
	

}
