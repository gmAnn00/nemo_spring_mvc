package com.springmvc.nemo.search.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.search.vo.SearchResultVO;

@Repository("searchDAO")
public class SearchDAOImpl implements SearchDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<SearchResultVO> search(Map<String, Object> searchMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.search.searchGroup", searchMap);
	}

}
