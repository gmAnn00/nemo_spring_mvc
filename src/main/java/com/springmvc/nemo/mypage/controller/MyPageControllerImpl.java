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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.mypage.service.MyPageService;
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
		
		//logger.info("myProfileVO="+myProfileVO.toString());
		//logger.info("interestsList="+interestsList.toString());
		
		
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
		Map<String, Object> userMap = new HashMap();
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
			//logger.info("이미지 변경함");
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
