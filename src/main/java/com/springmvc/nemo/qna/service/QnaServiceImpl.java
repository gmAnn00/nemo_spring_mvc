package com.springmvc.nemo.qna.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.qna.dao.QnaDAO;
import com.springmvc.nemo.qna.vo.QnaVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	private QnaDAO qnaDAO;
	
	
	@Override
	public Map<String, Object> listQna(Map<String, Object> pagingMap) throws DataAccessException {
		
		Map<String, Object> qnaMap = new HashMap<String, Object>();
		
		int totQna = qnaDAO.getTotQna();
		qnaMap.put("totQna", totQna);
		
		
		List<QnaVO> qnaList = new ArrayList<QnaVO>();
		
		int admin = (Integer) pagingMap.get("admin");
		
		if(admin == 1) {
			qnaList = qnaDAO.getAdminQnaList(pagingMap);
		}else {
			qnaList = qnaDAO.getUserQnaList(pagingMap);
		}
		
		
		qnaMap.put("qnaList", qnaList);
		
		return qnaMap;
	}

}
