package com.springmvc.nemo.user.service;

import java.util.UUID;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.springmvc.nemo.user.dao.LoginDAO;
import com.springmvc.nemo.user.vo.KakaoVO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	private static final String REST_API_KEY = "c73306afc68803d77548f1b3dea5d5c2";
	private static final String REDIRECT_URI = "http://127.0.0.1:8090/nemo/oauth";
	private static final String GRANT_TYPE = "authorization_code";
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public Boolean loginTry(UserVO userVO) throws DataAccessException {
		return loginDAO.loginTry(userVO);
	}
	
	@Override
	public void keepLogin(UserVO userVO) throws DataAccessException {
		
		loginDAO.keepLogin(userVO);
	}
	
	@Override
	public UserVO getUserBySessionId(String session_id) throws DataAccessException {
		
		return loginDAO.getUserBySessionId(session_id);
	}

	@Override
	public UserVO findUserById(String user_id) throws DataAccessException {
		return loginDAO.findUserById(user_id);
	}

	@Override
	public String findId(UserVO userVO) throws DataAccessException {
		
		return loginDAO.findId(userVO);
	}

	@Override
	public Boolean resetPasswordCheck(UserVO userVO) throws DataAccessException {
		
		return loginDAO.resetPasswordCheck(userVO);
	}

	@Override
	public void resetPassword(UserVO userVO) throws DataAccessException {
		
		loginDAO.resetPassword(userVO);
		
	}
	
	@Override
	public String getKakaoAccessToken(String code) throws Exception {

		String tokenURL = "https://kauth.kakao.com/oauth/token";
		String access_token = null;
		
		try {
			HttpResponse<JsonNode> response = Unirest.post(tokenURL)
					.header("Content-type", " application/x-www-form-urlencoded;charset=utf-8")
					.field("grant_type", GRANT_TYPE)
					.field("client_id", REST_API_KEY)
					.field("redirect_uri",REDIRECT_URI)
					.field("code", code)
					.asJson();
					
			logger.info("response={}", response.getBody());
			
			JSONObject jsonObject =  response.getBody().getObject();
			access_token = jsonObject.getString("access_token");
			
			//logger.info("access_token={}", access_token);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return access_token;
	}
	
	
	@Override
	public KakaoVO getKakaoUserInfo(String accessToken) throws Exception {
		
		String URL = "https://kapi.kakao.com/v2/user/me";
		
		KakaoVO kakaoVO = new KakaoVO();
		
		try {
			HttpResponse<JsonNode> response = Unirest.get(URL)
					.header("Content-type", "Content-type: application/x-www-form-urlencoded;charset=utf-8")
					.header("Authorization", "Bearer " + accessToken)
					.asJson();

			logger.info("response={}", response.getBody());
			
			JSONObject jsonObject = response.getBody().getObject();
			long id = jsonObject.getLong("id");
			String user_id = Long.toString(id);
			
			JSONObject kakao_account = jsonObject.getJSONObject("kakao_account");
			
			JSONObject profile = kakao_account.getJSONObject("profile");
			String kakao_img = profile.getString("profile_image_url");
			String nickname = profile.getString("nickname");
			
			
			boolean hasEmail = kakao_account.getBoolean("has_email");
			String email = null;
			if(hasEmail) {
				email = kakao_account.getString("email");
			}
			
			kakaoVO.setUser_id(user_id);
			kakaoVO.setKakao_img(kakao_img);
			kakaoVO.setNickname(nickname);
			kakaoVO.setKakao_mail(email);
			kakaoVO.setAccess_token(accessToken);
			
			logger.info("kakaoVO={}", kakaoVO.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return kakaoVO;
	}
	
	
	@Override
	public UserVO kakaoLogin(KakaoVO kakaoVO) throws Exception {
		
		boolean isExist = loginDAO.isAlreayKakaoUser(kakaoVO);
		
		UserVO userVO = new UserVO();
		
		if(isExist) {
			
			loginDAO.updateKakaoUser(kakaoVO);
			
			userVO = loginDAO.getUserInfo(kakaoVO.getUser_id());
			
		}else {
			
			loginDAO.insertKakaoUser(kakaoVO);
			
			userVO.setUser_id(kakaoVO.getUser_id());
			userVO.setNickname(kakaoVO.getNickname());
			userVO.setUser_img(UUID.randomUUID().toString()+".jpg");
			userVO.setAdmin(0);
			userVO.setSns_login(1);
			
			loginDAO.addUserTbl(userVO);
			
		}
		
		return userVO;
		
	}
	
	
	@Override
	public void kakaoLogout(String user_id) throws Exception {
		
		try {
			String access_token = loginDAO.getAccessToken(user_id);
			String logoutURL = "https://kapi.kakao.com/v1/user/logout";
			
			HttpResponse<JsonNode> response = Unirest.post(logoutURL)
					.header("Authorization", "Bearer " + access_token)
					.asJson();
			
			loginDAO.delAccessToken(user_id);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
