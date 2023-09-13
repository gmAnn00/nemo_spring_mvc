package com.springmvc.nemo.leader.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.user.vo.UserVO;

@Repository("leaderDAO")
public class LeaderDAOImpl implements LeaderDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public UserVO getUserInfo(String user_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.leader.getUserInfo", user_id);
	}
	
	@Override
	public List<UserVO> getMemberInfo(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.leader.getMemberInfo", group_id);
	}

}
