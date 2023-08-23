package com.springmvc.nemo.common.duplicate.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("duplicateDAO")
public class DuplicateDAOImpl implements DuplicateDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String idCheck(String user_id) {
//		System.out.println("dulicateDAO");
		return sqlSession.selectOne("mapper.duplicate.idCheck", user_id);
	}

	@Override
	public String nicknameCheck(String nickname) {
		return sqlSession.selectOne("mapper.duplicate.nicknameCheck", nickname);
	}

	@Override
	public String emailCheck(String email) {
		return sqlSession.selectOne("mapper.duplicate.emailCheck", email);
	}

}
