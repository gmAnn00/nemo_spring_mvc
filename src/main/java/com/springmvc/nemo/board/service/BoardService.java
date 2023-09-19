package com.springmvc.nemo.board.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface BoardService {

	public Map<String, Object> listBoard(Map<String, Object> pagingMap) throws DataAccessException;
	
}
