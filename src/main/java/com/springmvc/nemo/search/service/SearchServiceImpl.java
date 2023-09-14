package com.springmvc.nemo.search.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.springmvc.nemo.search.dao.SearchDAO;
import com.springmvc.nemo.search.vo.KaKaoGeoRes;
import com.springmvc.nemo.search.vo.SearchResultVO;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDAO searchDAO;

	@Override
	public int searchLength(Map<String, Object> searchMap) throws DataAccessException {

		return search(searchMap).size();
	}

	@Override
	public List<SearchResultVO> search(Map<String, Object> searchMap) throws DataAccessException {

		List<SearchResultVO> temp = searchDAO.search(searchMap);

		// 거리 필터
		int area = (int) searchMap.get("area");
		String _user_lat = (String) searchMap.get("user_lat");
		String _user_lng = (String) searchMap.get("user_lng");

		if (area != -1 && !_user_lat.equals("none") && !_user_lng.equals("none")) {
			double user_lat = Double.parseDouble(_user_lat);
			double user_lng = Double.parseDouble(_user_lng);
			String APIKey = "KakaoAK c73306afc68803d77548f1b3dea5d5c2";

			for (int i = temp.size() - 1; i >= 0; i--) {
				try {

					String groupAddr1 = temp.get(i).getGroup_addr1();
					String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query="
							+ URLEncoder.encode(groupAddr1, "UTF-8");

					HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", APIKey).asJson();
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

					KaKaoGeoRes bodyJson = objectMapper.readValue(response.getBody().toString(), KaKaoGeoRes.class);

					double lat1 = 0;
					double lng1 = 0;

					if (bodyJson.getDocuments().size() != 0) {
						lat1 = bodyJson.getDocuments().get(0).getY();
						lng1 = bodyJson.getDocuments().get(0).getX();
					}

					double lat2 = Math.toRadians(user_lat - lat1);
					double lng2 = Math.toRadians(user_lng - lng1);

					double dist = Math.sin(lat2 / 2) * Math.sin(lat2 / 2) + Math.cos(Math.toRadians(lat1))
							* Math.cos(Math.toRadians(user_lat / 2)) * Math.sin(lng2 / 2) * Math.sin(lng2 / 2);
					dist = 2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1 - dist));
					dist = 6371 * dist * 1000;

					if (dist > area * 1000) {
						temp.remove(i);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} // end of for

		} // end of if(area != -1 && !_user_lat.equals("none") &&
			// !_user_lng.equals("none"))

		return temp;
	}

	
	@Override
	public List<SearchResultVO> paging(Map<String, Object> searchMap, List<SearchResultVO> temp)
			throws DataAccessException {
		
		List<SearchResultVO> result = new ArrayList<SearchResultVO>();

		// 페이징 처리
		int total = temp.size();
		int section = (int) searchMap.get("section");
		int pagenum = (int) searchMap.get("pagenum");
		int start = (section - 1) * 100 + (pagenum - 1) * 10;
		int cnt = 10;

		if (section > total / 100 && pagenum > (total % 100) / 10 + 1) {
			// 마지막 섹션의 마지막 페이지일때
			cnt = total % 10;
		} else {
			cnt = 10;
		}

		result = temp.stream().skip(start).limit(cnt).collect(Collectors.toList());
		
		return result;

	}

}
