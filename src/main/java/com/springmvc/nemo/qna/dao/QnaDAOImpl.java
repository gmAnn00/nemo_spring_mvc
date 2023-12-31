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
	
	
	@Override
	public QnaVO getQna(int qna_no) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.qna.getQna", qna_no);
	}
	
	@Override
	public int getNewQnaNo() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.qna.getNewQnaNo");
	}
	
	
	@Override
	public void addQna(QnaVO qnaVO) throws DataAccessException {
		
		sqlSession.insert("mapper.qna.addQna", qnaVO);
	}
	
	
	@Override
	public void modQna(QnaVO qnaVO) throws DataAccessException {
		
		sqlSession.update("mapper.qna.modQna", qnaVO);
	}
	
	
	@Override
	public int getAccessible(Map<String, Object> accessMap) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.qna.getAccessible", accessMap);
	}
	
	
	@Override
	public void delQna(int qna_no) throws DataAccessException {
		
		sqlSession.delete("mapper.qna.delQna", qna_no);
	}
	
	@Override
	public List<QnaVO> adminSearchQna(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.qna.adminSearchQna", searchMap);
	}
	
	@Override
	public List<QnaVO> userSearchQna(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.qna.userSearchQna", searchMap);
	}
	
	@Override
	public int getTotAdminSearchQna(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.qna.getTotAdminSearchQna", searchMap);
	}
	
	@Override
	public int getTotUserSearchQna(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.qna.getTotUserSearchQna", searchMap);
	}
	
	
	
	

}
