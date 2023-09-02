package com.springmvc.nemo.group.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.group.vo.JoinVO;

public interface GroupDAO {

	public int getNewGroupId() throws DataAccessException;

	public void createGroup(Map<String, Object> groupMap) throws DataAccessException;

	public void joinGroup(JoinVO joinVO) throws DataAccessException;

}
