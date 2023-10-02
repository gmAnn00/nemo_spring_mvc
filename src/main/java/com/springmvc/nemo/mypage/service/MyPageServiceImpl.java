package com.springmvc.nemo.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.springmvc.nemo.board.vo.BoardVO;
import com.springmvc.nemo.board.vo.CommentVO;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.mypage.dao.MyPageDAO;
import com.springmvc.nemo.mypage.vo.CommingScheduleVO;
import com.springmvc.nemo.mypage.vo.ModInfoVO;
import com.springmvc.nemo.mypage.vo.MyProfileVO;
import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	private MyPageDAO myPageDAO;
	
	@Override
	public MyProfileVO findMyProfileById(String user_id) throws DataAccessException {
		
		return myPageDAO.findMyProfileById(user_id);
	}
	
	@Override
	public List<InterestsVO> findMyInterestsById(String user_id) throws DataAccessException {
		
		return myPageDAO.findMyInterestsById(user_id);
	}
	
	@Override
	public ModInfoVO findMyUserInfoById(String user_id) throws DataAccessException {
		
		return myPageDAO.findMyUserInfoById(user_id);
	}
	
	@Override
	public void modProfile(Map<String, Object> userMap) throws DataAccessException {
		myPageDAO.modProfile(userMap);
		
	}
	
	@Override
	public void modImage(Map<String, Object> userMap) throws DataAccessException {
		myPageDAO.modImage(userMap);
		
	}
	
	@Override
	public void modInterests(List<InterestsVO> interestsList) throws DataAccessException {
		String user_id = interestsList.get(0).getUser_id();
		myPageDAO.deleteInterests(user_id);
		myPageDAO.addInterests(interestsList);
	}
	
	@Override
	public boolean delUser(UserVO userVO) throws DataAccessException {
		boolean result = myPageDAO.checkPassword(userVO);
		
		// true : 아이디와 비밀번호 일치
		if(result) {
			myPageDAO.delUser(userVO.getUser_id());
			return true;
		}
		
		
		return false;
	}
	
	
	@Override
	public void delKakaoUser(String user_id) throws DataAccessException {
		
		String accessToken = myPageDAO.getAccessToken(user_id);
		
		try {
			HttpResponse<JsonNode> response = Unirest.post("https://kapi.kakao.com/v1/user/unlink")
					.header("Authorization", "Bearer " + accessToken)
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myPageDAO.delUser(user_id);
		myPageDAO.delKakaoUser(user_id);
		
	}
	
	@Override
	public List<CommingScheduleVO> getCommingSchedules(String user_id) throws DataAccessException {
		
		return myPageDAO.getCommingSchedules(user_id);
	}
	
	@Override
	public List<String> getSelectYMSchedule(Map<String, String> scheduleMap) throws DataAccessException {
		
		return myPageDAO.getSelectYMSchedule(scheduleMap);
	}
	
	@Override
	public List<CommingScheduleVO> getSelectMonthSchedule(Map<String, String> scheduleMap) throws DataAccessException {
		
		return myPageDAO.getSelectMonthSchedule(scheduleMap);
	}
	
	@Override
	public List<GroupVO> getLeaderGroup(String user_id) throws DataAccessException {
		
		return myPageDAO.getLeaderGroup(user_id);
	}
	
	@Override
	public List<GroupVO> getGroup(String user_id) throws DataAccessException {
		
		return myPageDAO.getGroup(user_id);
	}
	
	@Override
	public List<GroupVO> getWaitGroup(String user_id) throws DataAccessException {
		
		return myPageDAO.getWaitGroup(user_id);
	}
	
	@Override
	public List<GroupVO> getBookmarkGroup(String user_id) throws DataAccessException {
		
		return myPageDAO.getBookmarkGroup(user_id);
	}
	
	@Override
	public List<BoardVO> getMyBoardList(String user_id) throws DataAccessException {

		return myPageDAO.getMyBoardList(user_id);
	}
	
	@Override
	public List<CommentVO> getMyCommentList(String user_id) throws DataAccessException {
		
		return myPageDAO.getMyCommentList(user_id);
	}

}
