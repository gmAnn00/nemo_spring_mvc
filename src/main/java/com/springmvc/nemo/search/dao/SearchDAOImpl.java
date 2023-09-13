package com.springmvc.nemo.search.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("searchDAO")
public class SearchDAOImpl implements SearchDAO{
	
	@Autowired
	private SqlSession sqlSession;

}
