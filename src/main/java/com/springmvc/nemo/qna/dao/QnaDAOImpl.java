package com.springmvc.nemo.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.qna.vo.QnaVO;

@Repository("qnaDAO")
public class QnaDAOImpl implements QnaDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getTotQna() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.qna.getTotQna");
	}
	
	
	@Override
	public List<QnaVO> getAdminQnaList(Map<String, Object> pagingMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.qna.getAdminQnaList", pagingMap);
	}
	
	
	@Override
	public List<QnaVO> getUserQnaList(Map<String, Object> pagingMap) throws DataAccessException {

		return sqlSession.selectList("mapper.qna.getUserQnaList", pagingMap);
	}

}
