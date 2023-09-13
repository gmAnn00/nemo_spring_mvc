package com.springmvc.nemo.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.search.dao.SearchDAO;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDAO searchDAO;

}
