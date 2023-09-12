package com.springmvc.nemo.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.group.vo.JoinVO;
import com.springmvc.nemo.group.vo.WaitListVO;
import com.springmvc.nemo.user.vo.UserVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserVO getUserInfo(String user_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.member.getUserInfo", user_id);
	}
	
	@Override
	public List<UserVO> getMemberInfo(JoinVO joinVO) throws DataAccessException {
		
		return sqlSession.selectList("mapper.member.getMemberInfo", joinVO);
	}
	
	@Override
	public boolean isAlreadyCancel(JoinVO joinVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.member.isAlreadyCancel", joinVO);
	}
	
	@Override
	public void cancelGroup(JoinVO joinVO) throws DataAccessException {
		int group_id = joinVO.getGroup_id();
		sqlSession.update("mapper.member.cancelGroup", joinVO);
		sqlSession.update("mapper.member.decreaseCurrentMemNo", group_id);
	}
	
	@Override
	public void cancelWait(WaitListVO waitListVO) throws DataAccessException {
		
		sqlSession.delete("mapper.member.cancelWait", waitListVO);
	}

}
