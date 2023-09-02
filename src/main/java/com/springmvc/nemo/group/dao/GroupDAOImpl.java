package com.springmvc.nemo.group.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.group.vo.JoinVO;

@Repository("groupDAO")
public class GroupDAOImpl implements GroupDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getNewGroupId() throws DataAccessException {
		return sqlSession.selectOne("mapper.group.getNewGroupId");
	}
	
	@Override
	public void createGroup(Map<String, Object> groupMap) throws DataAccessException {
		
		sqlSession.insert("mapper.group.createGroup", groupMap);
	}
	
	@Override
	public void joinGroup(JoinVO joinVO) throws DataAccessException {
		sqlSession.insert("mapper.group.joinGroup", joinVO);
		
	}

}