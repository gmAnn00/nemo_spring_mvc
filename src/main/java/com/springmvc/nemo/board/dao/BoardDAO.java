package com.springmvc.nemo.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.board.vo.BoardVO;

public interface BoardDAO {

	public List<BoardVO> getNoticeList(int group_id) throws DataAccessException;

	public int getTotBoard(int group_id) throws DataAccessException;

	public List<BoardVO> getBoard(Map<String, Object> pagingMap) throws DataAccessException;

	 
}
