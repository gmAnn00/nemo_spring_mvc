package com.springmvc.nemo.admin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.user.vo.UserVO;

public interface AdminDAO {

	public List<UserVO> getUsersList() throws DataAccessException;

	public void delUser(String user_id) throws DataAccessException;

	public List<GroupVO> getGroupList() throws DataAccessException;

	public void delGroup(int group_id) throws DataAccessException;

}
