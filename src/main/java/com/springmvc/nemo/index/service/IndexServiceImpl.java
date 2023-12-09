package com.springmvc.nemo.index.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.index.dao.IndexDAO;

@Service("indexService")
public class IndexServiceImpl implements IndexService{
	
	private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
	
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
	
	// 사용자의 관심사와 관련된 소모임을 추천하는 Service
	@Override
	public List<GroupVO> getInterestsGroupList(String user_id) throws DataAccessException {
		
		// 소분류 카테고리까지 같은 소모임 검색
		List<GroupVO> interestsGroupList = indexDAO.getSubInterestsGroupList(user_id);
		
		if(interestsGroupList.size() < 4) {
			int supplement = 4 - interestsGroupList.size();
			
			List<Integer> groupIdList = new ArrayList<Integer>();
			for(GroupVO group : interestsGroupList) {
				groupIdList.add(group.getGroup_id());
			}
			
			Map<String, Object> supplementMap = new HashMap<String, Object>();
			supplementMap.put("user_id", user_id);
			supplementMap.put("supplement", supplement);
			supplementMap.put("groupIdList", groupIdList);
			List<GroupVO> supplementList = indexDAO.getMainInterestsGroupList(supplementMap);
			
			interestsGroupList.addAll(supplementList);
		}
		
		if(interestsGroupList.size() < 4) {
			int supplement = 4 - interestsGroupList.size();

			List<Integer> groupIdList = new ArrayList<Integer>();
			for(GroupVO group : interestsGroupList) {
				groupIdList.add(group.getGroup_id());
			}
			
			Map<String, Object> supplementMap = new HashMap<String, Object>();
			supplementMap.put("supplement", supplement);
			supplementMap.put("groupIdList", groupIdList);
			
			List<GroupVO> supplementList = indexDAO.getRandomGroupList(supplementMap);
			
			interestsGroupList.addAll(supplementList);
		}
		
		
		return interestsGroupList;
	}
	
	
	@Override
	public List<GroupVO> getNearGroupList(String user_id) throws DataAccessException {
		
		List<GroupVO> getNearGroupList = indexDAO.getNearGroupList(user_id);
		
		//logger.info("getNearGroupList1={}", getNearGroupList.toString());
		
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
			
			//logger.info("getNearGroupList2={}", getNearGroupList.toString());
		}
		
		return getNearGroupList;
	}

}
