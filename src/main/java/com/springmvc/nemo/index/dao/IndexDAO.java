package com.springmvc.nemo.index.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.GroupVO;

public interface IndexDAO {

	public List<GroupVO> getRandomGroupList() throws DataAccessException;

}
