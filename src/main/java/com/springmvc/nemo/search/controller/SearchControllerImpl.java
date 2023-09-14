package com.springmvc.nemo.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.search.service.SearchService;
import com.springmvc.nemo.search.vo.SearchResultVO;

@Controller("searchController")
public class SearchControllerImpl implements SearchController{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchControllerImpl.class);
	
	@Autowired
	private SearchService searchService;
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@Override
	public ModelAndView search(
			@RequestParam(value = "user_lat", defaultValue = "none", required = false) String user_lat,
			@RequestParam(value = "user_lng", defaultValue = "none", required = false) String user_lng,
			@RequestParam(value = "main_cate", defaultValue = "none", required = false) String main_cate,
			@RequestParam(value = "sub_cate", defaultValue = "none", required = false) String sub_cate,
			@RequestParam(value = "area", defaultValue = "-1", required = false) int area,
			@RequestParam(value = "keyword", defaultValue = "none", required = false) String keyword,
			@RequestParam(value = "joinable", defaultValue = "none", required = false) String joinable,
			@RequestParam(value = "sort", defaultValue = "name", required = false) String sort,
			@RequestParam(value = "section", defaultValue = "1", required = false) int section,
			@RequestParam(value = "pagenum", defaultValue = "1", required = false) int pagenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("user_id");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("user_id", user_id);
		searchMap.put("user_lat", user_lat);
		searchMap.put("user_lng", user_lng);
		searchMap.put("main_cate", main_cate);
		searchMap.put("sub_cate", sub_cate);
		searchMap.put("area", area);
		searchMap.put("keyword", keyword);
		searchMap.put("joinable", joinable);
		searchMap.put("sort", sort);
		searchMap.put("section", section);
		searchMap.put("pagenum", pagenum);
		
		int resultLength = searchService.searchLength(searchMap);
		
		List<SearchResultVO> resultList = new ArrayList<SearchResultVO>();
		resultList = searchService.search(searchMap);
		resultList = searchService.paging(searchMap, resultList);
		
		//logger.info("searchMap={}", searchMap.toString());
		
		//logger.info("resultList={}", resultList.toString());
		
		mav.addObject("searchMap", searchMap);
		mav.addObject("resultList", resultList);
		mav.addObject("resultLength", resultLength);
		
		
		return mav;
	}
	
	

}
