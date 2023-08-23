package com.springmvc.nemo.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.user.vo.UserVO;

@Repository("signUpDAO")
public class SignUpDAOImpl implements SignUpDAO{
	
	@Autowired
	private SqlSession sqlSession;

	
	// 회원가입
	@Override
	public void join(UserVO userVO) throws DataAccessException {
		sqlSession.insert("mapper.signUp.joinUser", userVO);
	}
	
	

}
