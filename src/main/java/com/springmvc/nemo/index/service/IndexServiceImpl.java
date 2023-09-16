package com.springmvc.nemo.index.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.index.dao.IndexDAO;

@Service("indexService")
public class IndexServiceImpl implements IndexService{
	
	@Autowired
	private IndexDAO indexDAO;
	
	
	@Override
	public List<GroupVO> getRandomGroupList(int supplement) throws DataAccessException {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(-1);
		
		map.put("supplement", supplement);
		map.put("groupIdList", temp);
		
		return indexDAO.getRandomGroupList(map);
	}
	
	@Override
	public List<GroupVO> getInterestsGroupList(String user_id) throws DataAccessException {
		
		List<GroupVO> interestGroupList = indexDAO.getSubInterestsGroupList(user_id);
		
		if(interestGroupList.size() < 4) {
			int supplement = 4 - interestGroupList.size();
			
			List<Integer> groupIdList = new ArrayList<Integer>();
			for(GroupVO group : interestGroupList) {
				groupIdList.add(group.getGroup_id());
			}
			
			Map<String, Object> supplementMap = new HashMap<String, Object>();
			supplementMap.put("user_id", user_id);
			supplementMap.put("supplement", supplement);
			supplementMap.put("groupIdList", groupIdList);
			List<GroupVO> supplementList = indexDAO.getMainInterestsGroupList(supplementMap);
			
			interestGroupList.addAll(supplementList);
		}
		
		if(interestGroupList.size() < 4) {
			int supplement = 4 - interestGroupList.size();

			List<Integer> groupIdList = new ArrayList<Integer>();
			for(GroupVO group : interestGroupList) {
				groupIdList.add(group.getGroup_id());
			}
			
			Map<String, Object> supplementMap = new HashMap<String, Object>();
			supplementMap.put("supplement", supplement);
			supplementMap.put("groupIdList", groupIdList);
			
			List<GroupVO> supplementList = indexDAO.getRandomGroupList(supplementMap);
			
			interestGroupList.addAll(supplementList);
		}
		
		
		
		return interestGroupList;
	}
	
	
	@Override
	public List<GroupVO> getNearGroupList(String user_id) throws DataAccessException {
		
		List<GroupVO> getNearGroupList = indexDAO.getNearGroupList(user_id);
		
		if(getNearGroupList.size() < 4) {
			int supplement = 4 - getNearGroupList.size();

			List<Integer> groupIdList = new ArrayList<Integer>();
			for(GroupVO group : getNearGroupList) {
				groupIdList.add(group.getGroup_id());
			}
			
			Map<String, Object> supplementMap = new HashMap<String, Object>();
			supplementMap.put("supplement", supplement);
			supplementMap.put("groupIdList", groupIdList);
			
			List<GroupVO> supplementList = indexDAO.getRandomGroupList(supplementMap);
			
			getNearGroupList.addAll(supplementList);
		}
		
		return getNearGroupList;
	}

}
