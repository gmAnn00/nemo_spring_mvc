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

	 
}
