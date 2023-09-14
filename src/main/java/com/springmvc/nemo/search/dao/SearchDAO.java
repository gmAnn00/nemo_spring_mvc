package com.springmvc.nemo.search.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.search.vo.SearchResultVO;

public interface SearchDAO {

	public List<SearchResultVO> search(Map<String, Object> searchMap) throws DataAccessException;

}
