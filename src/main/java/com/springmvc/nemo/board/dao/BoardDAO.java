package com.springmvc.nemo.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;

public interface BoardDAO {

	public List<BoardVO> getNoticeList(int group_id) throws DataAccessException;

	public int getTotBoard(int group_id) throws DataAccessException;

	public List<BoardVO> getBoardList(Map<String, Object> pagingMap) throws DataAccessException;

	public BoardVO getBoard(int article_no) throws DataAccessException;

	public List<CommentVO> getCommentsList(int article_no) throws DataAccessException;

	public void updateBoardViewCnt(int article_no) throws DataAccessException;

	public int getNewArticleNo() throws DataAccessException;

	public void addBoard(BoardVO boardVO) throws DataAccessException;

	public void modBoard(BoardVO boardVO) throws DataAccessException;

	public void delBoard(int article_no) throws DataAccessException;

	public List<BoardVO> searchBoard(Map<String, Object> searchMap) throws DataAccessException;

	public int getTotSearchBoard(Map<String, Object> searchMap) throws DataAccessException;

	public int getNewCommentNo() throws DataAccessException;

	public void addComment(CommentVO commentVO) throws DataAccessException;

	public CommentVO getCommentVOByNo(int newCommentNo) throws DataAccessException;

	public void increaseCommentCnt(int article_no) throws DataAccessException;

	 
}
