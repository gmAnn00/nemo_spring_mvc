package com.springmvc.nemo.group.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.GroupVO;

public interface GroupService {

	public int createGroup(Map<String, Object> groupMap) throws DataAccessException;


}
