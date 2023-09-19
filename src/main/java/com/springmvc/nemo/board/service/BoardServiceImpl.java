package com.springmvc.nemo.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.board.dao.BoardDAO;
import com.springmvc.nemo.board.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public Map<String, Object> listBoard(Map<String, Object> pagingMap) throws DataAccessException {
		
		int group_id = (Integer) pagingMap.get("group_id");
		
		List<BoardVO> noticeList = boardDAO.getNoticeList(group_id);
		
		int totBoard = boardDAO.getTotBoard(group_id);
		
		List<BoardVO> boardList =  boardDAO.getBoard(pagingMap);
		
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("noticeList", noticeList);
		boardMap.put("totBoard", totBoard);
		boardMap.put("boardList", boardList);
		
		return boardMap;
	}

}
