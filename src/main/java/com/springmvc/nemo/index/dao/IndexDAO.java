package com.springmvc.nemo.index.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.GroupVO;

public interface IndexDAO {

	public List<GroupVO> getRandomGroupList(Map<String, Object> supplementMap) throws DataAccessException;

	public List<GroupVO> getSubInterestsGroupList(String user_id) throws DataAccessException;

	public List<GroupVO> getMainInterestsGroupList(Map<String, Object> supplementMap) throws DataAccessException;

	public List<GroupVO> getNearGroupList(String user_id) throws DataAccessException;

}
