package com.springmvc.nemo.group.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.group.dao.GroupDAO;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.group.vo.JoinVO;

@Service("groupService")
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Override
	public int createGroup(Map<String, Object> groupMap) throws DataAccessException {
		int group_id = groupDAO.getNewGroupId();
		Map<String, Object> newGroupMap = groupMap;
		newGroupMap.put("group_id", group_id);
		
		groupDAO.createGroup(newGroupMap);
		
		JoinVO joinVO = new JoinVO();
		joinVO.setGroup_id(group_id);
		joinVO.setUser_id((String)newGroupMap.get("group_leader"));
		groupDAO.joinGroup(joinVO);
		
		return group_id;
	}
}
