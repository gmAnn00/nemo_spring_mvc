package com.springmvc.nemo.qna.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.qna.vo.QnaVO;

public interface QnaService {

	public Map<String, Object> listQna(Map<String, Object> pagingMap) throws DataAccessException;

	public QnaVO getQna(int qna_no) throws DataAccessException;

	public int addQna(QnaVO qnaVO) throws DataAccessException;

	public void modQna(QnaVO qnaVO) throws DataAccessException;

	public boolean getAccessible(Map<String, Object> accessMap) throws DataAccessException;

	public void delQna(int qna_no) throws DataAccessException;
	

}
