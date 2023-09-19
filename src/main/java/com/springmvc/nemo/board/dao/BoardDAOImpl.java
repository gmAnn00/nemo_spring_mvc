package com.springmvc.nemo.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.board.vo.BoardVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVO> getNoticeList(int group_id) throws DataAccessException {
		
		return sqlSession.selectList("getNoticeList", group_id);
	}
	
	
	@Override
	public int getTotBoard(int group_id) throws DataAccessException {
		
		return sqlSession.selectOne("getTotBoard", group_id);
	}
	
	@Override
	public List<BoardVO> getBoard(Map<String, Object> pagingMap) throws DataAccessException {
		
		return sqlSession.selectList("getBoard", pagingMap);
	}

}
