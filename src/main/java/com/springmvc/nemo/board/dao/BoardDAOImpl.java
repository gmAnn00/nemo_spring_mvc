package com.springmvc.nemo.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;

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
	public List<BoardVO> getBoardList(Map<String, Object> pagingMap) throws DataAccessException {
		
		return sqlSession.selectList("getBoardList", pagingMap);
	}
	
	
	@Override
	public BoardVO getBoard(int article_no) throws DataAccessException {
		
		return sqlSession.selectOne("getBoard", article_no);
	}
	
	@Override
	public List<CommentVO> getCommentsList(int article_no) throws DataAccessException {
		
		return sqlSession.selectList("getCommentsList", article_no);
	}
	
	
	@Override
	public void updateBoardViewCnt(int article_no) throws DataAccessException {
		
		sqlSession.update("updateBoardViewCnt", article_no);
	}
	
	
	@Override
	public int getNewArticleNo() throws DataAccessException {
		
		return sqlSession.selectOne("getNewArticleNo");
	}
	
	@Override
	public void addBoard(BoardVO boardVO) throws DataAccessException {
		
		sqlSession.insert("addBoard", boardVO);
	}
	
	@Override
	public void modBoard(BoardVO boardVO) throws DataAccessException {
		
		sqlSession.update("modBoard", boardVO);
	}
	
	
	@Override
	public void delBoard(int article_no) throws DataAccessException {
		
		sqlSession.delete("delBoard", article_no);
	}
	
	@Override
	public List<BoardVO> searchBoard(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectList("searchBoard", searchMap);
	}
	
	@Override
	public int getTotSearchBoard(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectOne("getTotSearchBoard", searchMap);
	}

}
