package com.springmvc.nemo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.admin.dao.AdminDAO;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List<UserVO> getUsersList() throws DataAccessException {
		
		return adminDAO.getUsersList();
	}
	
	@Override
	public void delUser(String user_id) throws DataAccessException {
		
		adminDAO.delUser(user_id);
	}
	
	@Override
	public List<GroupVO> getGroupList() throws DataAccessException {

		return adminDAO.getGroupList();
	}
	
	@Override
	public void delGroup(int group_id) throws DataAccessException {
		
		adminDAO.delGroup(group_id);
		
	}

}
