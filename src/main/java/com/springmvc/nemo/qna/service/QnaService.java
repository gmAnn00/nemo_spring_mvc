package com.springmvc.nemo.qna.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface QnaService {

	Map<String, Object> listQna(Map<String, Object> pagingMap) throws DataAccessException;
	

}
