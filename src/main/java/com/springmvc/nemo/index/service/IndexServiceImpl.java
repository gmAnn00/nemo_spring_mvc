package com.springmvc.nemo.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.index.dao.IndexDAO;

@Service("indexService")
public class IndexServiceImpl implements IndexService{
	
	@Autowired
	private IndexDAO indexDAO;
	
	
	@Override
	public List<GroupVO> getRandomGroupList() throws DataAccessException {
		
		return indexDAO.getRandomGroupList();
	}

}
