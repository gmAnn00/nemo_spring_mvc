package com.springmvc.nemo.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.search.dao.SearchDAO;
import com.springmvc.nemo.search.vo.SearchResultVO;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDAO searchDAO;
	
	
	@Override
	public List<SearchResultVO> search(Map<String, Object> searchMap) throws DataAccessException {
		
		return searchDAO.search(searchMap);
	}

}
