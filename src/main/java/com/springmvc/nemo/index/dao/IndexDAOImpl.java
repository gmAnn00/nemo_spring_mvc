package com.springmvc.nemo.index.dao;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.search.vo.KaKaoGeoRes;

@Repository("indexDAO")
public class IndexDAOImpl implements IndexDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(IndexDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<GroupVO> getRandomGroupList(Map<String, Object> supplementMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.index.getRandomGroupList", supplementMap);
	}
	
	@Override
	public List<GroupVO> getSubInterestsGroupList(String user_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.index.getSubInterestsGroupList", user_id);
	}
	
	@Override
	public List<GroupVO> getMainInterestsGroupList(Map<String, Object> supplementMap) throws DataAccessException {
		
		return sqlSession.selectList("mapper.index.getMainInterestsGroupList", supplementMap);
	}
	
	@Override
	public List<GroupVO> getNearGroupList(String user_id) throws DataAccessException {
		
		// 모든 소모임 가져옴
		List<GroupVO> allGroupList = sqlSession.selectList("mapper.index.getAllGroupList");
		String userAddr = sqlSession.selectOne("mapper.index.getUserAddr", user_id);
		
		String APIKey = "KakaoAK c73306afc68803d77548f1b3dea5d5c2";
		double userLat = 0.0;
		double userLnt = 0.0;
		
		// 유저의 위치를 위도 경도로 변환
		try {
			
			String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" 
                    + URLEncoder.encode(userAddr, "UTF-8");
			
			HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", APIKey).asJson();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			
			// 카카오로컬에서 받은 json 정보를 클래스로 바꿔줌
			KaKaoGeoRes bodyJson = objectMapper.readValue(response.getBody().toString(), KaKaoGeoRes.class);
		
			if(bodyJson.getDocuments().size() != 0) {
				userLat = bodyJson.getDocuments().get(0).getY();
				userLnt = bodyJson.getDocuments().get(0).getX();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		try {
			// 소모임의 위치를 위도 경도로 변환한 후 사용자의 위도 경도와의 거리를 계산	
			for(int i = allGroupList.size()-1; i >=0; i--) {
				
				GroupVO group = (GroupVO) allGroupList.get(i);
				String address = group.getGroup_addr1();
				String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" 
	                    + URLEncoder.encode(address, "UTF-8");
				
				HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", APIKey).asJson();
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				
				KaKaoGeoRes bodyJson = objectMapper.readValue(response.getBody().toString(), KaKaoGeoRes.class);
			
				double groupLat = 0.0;
				double groupLng = 0.0;
				
				if(bodyJson.getDocuments().size() > 0) {
					groupLat = bodyJson.getDocuments().get(0).getY();
					groupLng = bodyJson.getDocuments().get(0).getX();
				}
				
				double lat = Math.toRadians(userLat - groupLat);
				double lng = Math.toRadians(userLnt - groupLng);
				
				double dist = Math.sin(lat/2) * Math.sin(lat/2) +
						Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(userLat/2))
						* Math.sin(lng/2) * Math.sin(lng/2);
				dist = 2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1-dist));
				dist = 6371 * dist * 1000;
				
				// 거리가 20km가 넘을 경우 List에서 제외
				if(dist > 2000) {
					allGroupList.remove(i);
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 거리가 20km이내인 소모임을 섞은 후 4개 추출
		Collections.shuffle(allGroupList);
		if(allGroupList.size() > 4) {
			allGroupList = allGroupList.subList(0, 4);
		}
		
		return allGroupList;
	}

}
