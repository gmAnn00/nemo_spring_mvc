package com.springmvc.nemo.common.duplicate.dao;

import org.springframework.dao.DataAccessException;

public interface DuplicateDAO {
	
	public String idCheck(String user_id) throws DataAccessException;
	
	public String nicknameCheck(String nickname) throws DataAccessException;
	
	public String emailCheck(String email) throws DataAccessException;

}
