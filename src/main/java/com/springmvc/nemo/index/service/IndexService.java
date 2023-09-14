package com.springmvc.nemo.index.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.GroupVO;

public interface IndexService {

	public List<GroupVO> getRandomGroupList() throws DataAccessException;

	
	
}
