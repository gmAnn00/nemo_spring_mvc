package com.springmvc.nemo.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;

public interface BoardService {

	public Map<String, Object> listBoard(Map<String, Object> pagingMap) throws DataAccessException;

	public BoardVO getBoard(int article_no) throws DataAccessException;

	public List<CommentVO> getCommentsList(int article_no) throws DataAccessException;

	public void updateBoardViewCnt(int article_no) throws DataAccessException;

	public int addBoard(BoardVO boardVO) throws DataAccessException;

	
}
