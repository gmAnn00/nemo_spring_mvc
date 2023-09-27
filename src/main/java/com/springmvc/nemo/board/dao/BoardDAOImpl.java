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
		
		return sqlSession.selectList("mapper.board.getNoticeList", group_id);
	}
	
	
	@Override
	public int getTotBoard(int group_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getTotBoard", group_id);
	}
	
	@Override
	public List<BoardVO> getBoardList(Map<String, Object> pagingMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.board.getBoardList", pagingMap);
	}
	
	
	@Override
	public BoardVO getBoard(int article_no) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getBoard", article_no);
	}
	
	@Override
	public List<CommentVO> getCommentsList(int article_no) throws DataAccessException {
		
		return sqlSession.selectList("mapper.board.getCommentsList", article_no);
	}
	
	
	@Override
	public void updateBoardViewCnt(int article_no) throws DataAccessException {
		
		sqlSession.update("mapper.board.updateBoardViewCnt", article_no);
	}
	
	
	@Override
	public int getNewArticleNo() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getNewArticleNo");
	}
	
	@Override
	public void addBoard(BoardVO boardVO) throws DataAccessException {
		
		sqlSession.insert("mapper.board.addBoard", boardVO);
	}
	
	@Override
	public void modBoard(BoardVO boardVO) throws DataAccessException {
		
		sqlSession.update("mapper.board.modBoard", boardVO);
	}
	
	
	@Override
	public void delBoard(int article_no) throws DataAccessException {
		
		sqlSession.delete("mapper.board.delBoard", article_no);
	}
	
	@Override
	public List<BoardVO> searchBoard(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.board.searchBoard", searchMap);
	}
	
	@Override
	public int getTotSearchBoard(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getTotSearchBoard", searchMap);
	}
	
	@Override
	public int getNewCommentNo() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getNewCommenteNo");
	}
	
	@Override
	public void addComment(CommentVO commentVO) throws DataAccessException {
		
		sqlSession.insert("mapper.board.addComment", commentVO);
	}
	
	@Override
	public CommentVO getCommentVOByNo(int newCommentNo) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getCommentVOByNo", newCommentNo);
	}

	
	@Override
	public void increaseCommentCnt(int article_no) throws DataAccessException {
		sqlSession.update("mapper.board.increaseCommentCnt", article_no);
		
	}
	
	@Override
	public String getUserIdByCommentNo(int comment_no) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getUserIdByCommentNo", comment_no);
	}
	
	@Override
	public void modComment(CommentVO commentVO) throws DataAccessException {
		
		sqlSession.update("mapper.board.modComment", commentVO);
	}
	
	
	@Override
	public String getContentByCommentNo(int comment_no) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.getContentByCommentNo", comment_no);
	}
	
	
	@Override
	public void delComment(int comment_no) throws DataAccessException {
		
		sqlSession.delete("mapper.board.delComment", comment_no);
	}
	
	
	@Override
	public void decreaseCommentCnt(int article_no) throws DataAccessException {
		
		sqlSession.update("mapper.board.decreaseCommentCnt", article_no);
	}
}
