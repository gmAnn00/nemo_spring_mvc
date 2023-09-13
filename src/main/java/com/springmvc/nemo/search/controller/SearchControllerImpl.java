package com.springmvc.nemo.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.springmvc.nemo.search.service.SearchService;

@Controller("searchController")
public class SearchControllerImpl implements SearchController{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchControllerImpl.class);
	
	@Autowired
	private SearchService searchService;
	
	

}
