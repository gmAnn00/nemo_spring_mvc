package com.springmvc.nemo.mypage.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.mypage.service.MyPageService;
import com.springmvc.nemo.mypage.vo.CommingScheduleVO;
import com.springmvc.nemo.mypage.vo.ModInfoVO;
import com.springmvc.nemo.mypage.vo.MyProfileVO;
import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("myPageController")
public class MyPageControllerImpl implements MyPageController{
	private static final Logger logger = LoggerFactory.getLogger(MyPageControllerImpl.class);
	
	private static String USER_IMG_REPO;
	private static String USER_IMG_DEFAULT;
	
	@Autowired
	private MyPageService myPageService;

	@RequestMapping(value = "/mypage/myprofile", method = RequestMethod.GET)
	@Override
	public ModelAndView myProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		MyProfileVO myProfileVO = myPageService.findMyProfileById(user_id);
		List<InterestsVO> interestsList = myPageService.findMyInterestsById(user_id);
		
		//logger.info("myProfileVO={}",myProfileVO.toString());
		//logger.info("interestsList={}",interestsList.toString());
		
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("userVO", myProfileVO);
		mav.addObject("interestsList", interestsList);
		
		return mav;
	}
	
	@RequestMapping(value = "/mypage/modprofileform", method = RequestMethod.GET)
	@Override
	public ModelAndView modProfileForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		ModInfoVO modInfoVO = myPageService.findMyUserInfoById(user_id);
		
		//이메일 분리하기
		String email = modInfoVO.getEmail();
		int idx = email.indexOf("@");
		String emailId = email.substring(0, idx);
		String emailDomain = email.substring(idx+1);
		
		//logger.info("modInfoVO="+modInfoVO.toString());
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("userVO", modInfoVO);
		mav.addObject("emailId", emailId);
		mav.addObject("emailDomain", emailDomain);
		return mav;
	}
	
	@RequestMapping(value = "/mypage/modprofile", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public ResponseEntity modProfile(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> userMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		//logger.info("enum={}", enu.toString());
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			userMap.put(name, value);
		}
				
		HttpSession session = multipartRequest.getSession();
		String user_id = (String) session.getAttribute("user_id");
		userMap.put("user_id", user_id);
		
		// 이미지 파일 업로드
		USER_IMG_REPO = this.getClass().getResource("").getPath();
		USER_IMG_REPO = USER_IMG_REPO.substring(1, USER_IMG_REPO.indexOf(".metadata"));
		USER_IMG_REPO = USER_IMG_REPO.replace("/", "\\");
		USER_IMG_REPO += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\userImages\\";
		
		String originalFileName = (String) userMap.get("originalFileName");
		String isDeleteImg = (String) userMap.get("isDeleteImg");
		String user_img = upload(multipartRequest, isDeleteImg);
		//logger.info("user_img1={}", user_img);
		
		String message = "";
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		//logger.info("user_map={}", userMap.toString());
		
		try {

			if(user_img != null) {
				// 이미지 변경함 또는 삭제함
				File srcFile = new File(USER_IMG_REPO + "\\temp\\" + user_img);
				File destDir = new File(USER_IMG_REPO + "\\" + user_id);
				destDir.mkdirs();
				FileUtils.cleanDirectory(destDir);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				srcFile.delete();
			}else{
				// 이미지 그대로
				user_img = originalFileName;
			}
			//logger.info("user_img2={}", user_img);
			userMap.put("user_img", user_img);
			myPageService.modProfile(userMap);
			
			message = "<script>";
			message += "alert('회원정보를 수정하였습니다.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/mypage/myprofile';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
			String nickname = (String) userMap.get("nickname");
			session.removeAttribute("nickname");
			session.setAttribute("nickname", nickname);
			session.removeAttribute("user_img");
			session.setAttribute("user_img", user_img);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			File srcFile = new File(USER_IMG_REPO + "\\temp\\" + user_img);
			srcFile.delete();
			message = "<script>";
			message += "alert('회원정보 수정에 실패하였습니다.')";
			message += "location.href='" + multipartRequest.getContextPath() + "/mypage/myprofile';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		}

		return resEnt;
	}
	
	@RequestMapping(value = "/mypage/userimageupload", method = RequestMethod.POST)
	@Override
	public ResponseEntity modImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> userMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		//logger.info("enum={}", enu.toString());
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			userMap.put(name, value);
		}
		
		HttpSession session = multipartRequest.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		userMap.put("user_id", user_id);

		// 이미지 파일 업로드
		USER_IMG_REPO = this.getClass().getResource("").getPath();
		USER_IMG_REPO = USER_IMG_REPO.substring(1, USER_IMG_REPO.indexOf(".metadata"));
		USER_IMG_REPO = USER_IMG_REPO.replace("/", "\\");
		USER_IMG_REPO += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\userImages\\";
		
		String originalFileName = (String) userMap.get("originalFileName");
		String user_img = upload(multipartRequest, "false");
		//logger.info("user_img1={}", user_img);
		
		String message = "";
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		//logger.info("user_map={}", userMap.toString());
		
		try {

			if(user_img != null) {
				// 이미지 변경함 또는 삭제함
				File srcFile = new File(USER_IMG_REPO + "\\temp\\" + user_img);
				File destDir = new File(USER_IMG_REPO + "\\" + user_id);
				destDir.mkdirs();
				FileUtils.cleanDirectory(destDir);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				srcFile.delete();
			}else{
				// 이미지 그대로
				user_img = originalFileName;
			}
			//logger.info("user_img2={}", user_img);
			userMap.put("user_img", user_img);
			myPageService.modImage(userMap);
			
			session.removeAttribute("user_img");
			session.setAttribute("user_img", user_img);
			
			message = "<script>";
			message += "alert('프로필 이미지를 변경하였습니다.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/mypage/myprofile';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			File srcFile = new File(USER_IMG_REPO + "\\temp\\" + user_img);
			srcFile.delete();
			message = "<script>";
			message += "alert('프로필 이미지 수정에 실패하였습니다.')";
			message += "location.href='" + multipartRequest.getContextPath() + "/mypage/myprofile';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		}

		return resEnt;
		
	}
	
	@RequestMapping(value = "/mypage/modinterestsform", method = RequestMethod.GET)
	@Override
	public ModelAndView modInterestsForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		List<InterestsVO> interestsList = myPageService.findMyInterestsById(user_id);
		
		//logger.info("interestsList={}",interestsList.toString());
		
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("interestsList", interestsList);
		
		return mav;
	}
	
	@RequestMapping(value = "/mypage/modinterests", method = RequestMethod.POST)
	@Override
	public ModelAndView modInterests(@RequestParam("interests") String interests,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		JsonArray jsonArray = new Gson().fromJson(interests, JsonArray.class);
		
		List<InterestsVO> interestsList = new ArrayList<InterestsVO>();
		for(JsonElement elem : jsonArray) {
			JsonObject interestObj = elem.getAsJsonObject();
			
			InterestsVO interestsVO = new InterestsVO();
			interestsVO.setUser_id(user_id);
			interestsVO.setMain_cate(interestObj.get("main_cate").getAsString());
			interestsVO.setSub_cate(interestObj.get("sub_cate").getAsString());
			
			interestsList.add(interestsVO);
		}
		
		myPageService.modInterests(interestsList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", new Message("관심사를 수정하였습니다.", request.getContextPath()+"/mypage/myprofile"));
		mav.setViewName("message");
		
		return mav;
	}
	
	@RequestMapping(value = "/mypage/deluserform", method = RequestMethod.GET)
	@Override
	public ModelAndView delUserForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/mypage/deluser", method = RequestMethod.POST)
	@Override
	public ModelAndView delUser(@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		UserVO userVO = new UserVO();
		userVO.setUser_id(user_id);
		userVO.setPassword(password);
		
		// true : 탈퇴 성공, false : 비밀번호 틀림. 탈퇴 실패
		boolean result = myPageService.delUser(userVO);
		
		
		ModelAndView mav = new ModelAndView();
		
		if(result) {
			session.invalidate();
			mav.addObject("data", new Message("네모 탈퇴를 완료하였습니다.", request.getContextPath()+"/index"));
			
		} else {
			mav.addObject("data", new Message("비밀번호가 일치하지 않습니다.", request.getContextPath()+"/mypage/deluserform"));
		}
		mav.setViewName("message");
		return mav;
	}
	
	@RequestMapping(value = "/mypage/myschedule", method = RequestMethod.GET)
	@Override
	public ModelAndView mySchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		// 내 일정 정보 추가
		
		// 다가오는 일정
		List<CommingScheduleVO> commingScheduleList = myPageService.getCommingSchedules(user_id);
		
		mav.addObject("commingScheduleList", commingScheduleList);
		
		return mav;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/mypage/myschedule/ajaxschedule", method = RequestMethod.GET, produces="application/text;charset=utf-8")
	@Override
	public String ajaxSchedule(
			@RequestParam("year") String year, @RequestParam("month") String month,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		String selectYM = year + "/" + month;
		
		Map<String, String> scheduleMap = new HashMap<String, String>();
		scheduleMap.put("user_id", user_id);
		scheduleMap.put("selectYM", selectYM);
		
		List<String> scheduleList = myPageService.getSelectYMSchedule(scheduleMap);
		
		Gson gson = new Gson();
		String scheduleInfo = gson.toJson(scheduleList);
			
		return scheduleInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/mypage/myschedule/ajaxselectmonthschedule", method = RequestMethod.GET, produces="application/text;charset=utf-8")
	@Override
	public String ajaxSelectMonthSchedule(String year, String month, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		String selectYM = year + "/" + month;
		
		Map<String, String> scheduleMap = new HashMap<String, String>();
		scheduleMap.put("user_id", user_id);
		scheduleMap.put("selectYM", selectYM);
		
		List<CommingScheduleVO> selectMonthScheduleList = myPageService.getSelectMonthSchedule(scheduleMap);
		
		Gson gson = new Gson();
		String scheduleInfo = gson.toJson(selectMonthScheduleList);
		
		logger.info("scheduleInfo={}", scheduleInfo);
		
		return scheduleInfo;
	}
	
	@RequestMapping(value = "/mypage/mygroup", method = RequestMethod.GET)
	@Override
	public ModelAndView myGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		// 내 소모임 정보 추가
		
		// 내가 리더인 소모임
		List<GroupVO> leaderGroupList = myPageService.getLeaderGroup(user_id);
		mav.addObject("leaderGroupList", leaderGroupList);
		
		// 일반 회원인 소모임
		List<GroupVO> groupList = myPageService.getGroup(user_id);
		mav.addObject("groupList", groupList);
		
		// 가입 대기중인 소모임
		List<GroupVO> waitGroupList = myPageService.getWaitGroup(user_id);
		mav.addObject("waitGroupList", waitGroupList);
		
		// 찜한 소모임
		List<GroupVO> bookmarkGroupList = myPageService.getBookmarkGroup(user_id);
		mav.addObject("bookmarkGroupList", bookmarkGroupList);
		
		return mav;
	}
	
	@RequestMapping(value = "/mypage/myboard", method = RequestMethod.GET)
	@Override
	public ModelAndView myBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		// 내가 쓴글 정보 추가
		
		return mav;
	}
	
	
	private String upload(MultipartHttpServletRequest multipartRequest, String isDeleteImg) throws Exception {
		String imageFileName = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		if(isDeleteImg.equals("true")) {
			logger.info("이미지 삭제함");
			imageFileName = "pingoo.jpg";
			
			USER_IMG_DEFAULT = this.getClass().getResource("").getPath();
			USER_IMG_DEFAULT = USER_IMG_DEFAULT.substring(1, USER_IMG_DEFAULT.indexOf(".metadata"));
			USER_IMG_DEFAULT = USER_IMG_DEFAULT.replace("/", "\\");
			USER_IMG_DEFAULT += "nemo_spring_mvc\\src\\main\\webapp\\resources\\images\\pingoo.jpg";
			
			File srcFile = new File(USER_IMG_DEFAULT);
			File destDir = new File(USER_IMG_REPO + "\\temp");
			destDir.mkdir();
			FileUtils.copyFileToDirectory(srcFile, destDir, true);
			
			return imageFileName;

		}
		
		while (fileNames.hasNext()) {
			//logger.info("이미지 변경 또는 삭제");
			String fileName = fileNames.next();
			logger.info("fileName={}", fileName);
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			//logger.info("imageFileName={}", imageFileName);
			
			if(isDeleteImg.equals("false") && (imageFileName == null || imageFileName.equals("") || imageFileName.length() == 0)) {
				//logger.info("이미지 그대로");
				return null;
			}
			
			//File file = new File(USER_IMG_REPO + "\\temp\\" + fileName);
			if (mFile.getSize() != 0) {
				/*
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}*/
				mFile.transferTo(new File(USER_IMG_REPO + "\\temp\\" + imageFileName));
			}
		}

		return imageFileName;
	} // end of upload
	
}
