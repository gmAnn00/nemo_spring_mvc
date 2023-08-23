package com.springmvc.nemo.common.duplicate.service;

import org.springframework.dao.DataAccessException;

public interface DuplicateService {

public String idCheck(String user_id) throws DataAccessException;
	
	public String nicknameCheck(String nickname) throws DataAccessException;
	
	public String emailCheck(String email) throws DataAccessException;
	
}
