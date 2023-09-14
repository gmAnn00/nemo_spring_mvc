package com.springmvc.nemo.search.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface SearchController {
	
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
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
