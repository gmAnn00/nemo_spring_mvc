package com.springmvc.nemo.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.user.vo.UserVO;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<UserVO> getUsersList() throws DataAccessException {
		
		return sqlSession.selectList("mapper.admin.getUsersList");
	}
	
	
	@Override
	public void delUser(String user_id) throws DataAccessException {
		
		sqlSession.delete("mapper.admin.delUser", user_id);
	}
	
	
	@Override
	public List<GroupVO> getGroupList() throws DataAccessException {

		return sqlSession.selectList("mapper.admin.getGroupList");
	}

	@Override
	public void delGroup(int group_id) throws DataAccessException {
		
		sqlSession.delete("mapper.admin.delGroup", group_id);
	}
}
