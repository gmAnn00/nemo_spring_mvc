package com.springmvc.nemo.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.mypage.vo.ModInfoVO;
import com.springmvc.nemo.mypage.vo.MyProfileVO;
import com.springmvc.nemo.user.vo.InterestsVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MyProfileVO findMyProfileById(String user_id) throws DataAccessException {

		return sqlSession.selectOne("mapper.myPage.findMyProfileById", user_id);
	}
	
	@Override
	public List<InterestsVO> findMyInterestsById(String user_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.myPage.findMyInterestsById", user_id);
	}
	
	@Override
	public ModInfoVO findMyUserInfoById(String user_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.myPage.findMyUserInfoById", user_id);
	}
	
	@Override
	public void modProfile(Map userMap) throws DataAccessException {
		sqlSession.update("mapper.myPage.modProfile", userMap);
		
	}

}
