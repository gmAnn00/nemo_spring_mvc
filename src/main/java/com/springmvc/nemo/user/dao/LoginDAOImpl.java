package com.springmvc.nemo.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.user.vo.UserVO;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Boolean loginTry(UserVO userVO) throws DataAccessException {
		Boolean result = Boolean.parseBoolean(sqlSession.selectOne("mapper.login.loginTry", userVO));
		
		return result;
	}

	@Override
	public UserVO findUserById(String user_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.login.findUserById", user_id);
	}

	@Override
	public String findId(UserVO userVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.login.findId", userVO);
	}
	
	@Override
	public Boolean resetPasswordCheck(UserVO userVO) throws DataAccessException {
	
		return Boolean.parseBoolean(sqlSession.selectOne("mapper.login.resetPasswordCheck", userVO));
	}

	@Override
	public void resetPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.login.resetPassword", userVO);
		
	}

	
	
	
	
	

}