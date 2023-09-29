package com.springmvc.nemo.qna.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.qna.vo.QnaVO;

public interface QnaDAO {

	public int getTotQna() throws DataAccessException;

	public List<QnaVO> getAdminQnaList(Map<String, Object> pagingMap) throws DataAccessException;

	public List<QnaVO> getUserQnaList(Map<String, Object> pagingMap) throws DataAccessException;

	public QnaVO getQna(int qna_no) throws DataAccessException;

	public int getNewQnaNo() throws DataAccessException;

	public void addQna(QnaVO qnaVO) throws DataAccessException;

	public void modQna(QnaVO qnaVO) throws DataAccessException;

}
