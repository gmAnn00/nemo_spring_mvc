package com.springmvc.nemo.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.search.vo.SearchResultVO;

public interface SearchService {

	public List<SearchResultVO> search(Map<String, Object> searchMap) throws DataAccessException;

	public int searchLength(Map<String, Object> searchMap) throws DataAccessException;
	
	public List<SearchResultVO> paging(Map<String, Object> searchMap, List<SearchResultVO> temp)
			throws DataAccessException;


}
