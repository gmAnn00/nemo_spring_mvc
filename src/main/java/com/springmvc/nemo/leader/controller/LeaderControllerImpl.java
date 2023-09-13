package com.springmvc.nemo.leader.controller;

import java.io.File;
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

import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.group.vo.GroupVO;
import com.springmvc.nemo.leader.service.LeaderService;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("leaderController")
public class LeaderControllerImpl implements LeaderController{
	
	private static final Logger logger = LoggerFactory.getLogger(LeaderControllerImpl.class);
	
	private static String GROUP_IMG_REPO;
	private static String GROUP_IMG_DEFAULT;
	
	@Autowired
	private LeaderService leaderService;
	
	@RequestMapping(value = "/group/leader/memberinfo", method = RequestMethod.GET)
	@Override
	public ModelAndView memberInfo(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");

		UserVO user = leaderService.getUserInfo(user_id);
		
		List<UserVO> membersList = leaderService.getMemberInfo(group_id);
		List<UserVO> waitUsersList = leaderService.getWaitUserInfo(group_id);
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("user", user);
		mav.addObject("membersList", membersList);
		mav.addObject("waitUsersList", waitUsersList);
		
		return mav;
	}
	
	@RequestMapping(value = "/group/leader/mandate", method = RequestMethod.GET)
	@Override
	public ModelAndView mandate(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> mandateMap = new HashMap<String, Object>();
		mandateMap.put("group_id", group_id);
		mandateMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		boolean isGroupMemberResult = leaderService.isGroupMember(mandateMap);
		
		if(isGroupMemberResult) {
			String msg = leaderService.mandateLeader(mandateMap) + "님에게 소모임 리더를 위임했습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/groupmain?group_id="+group_id));
		}else {
			mav.addObject("data", new Message("해당 회원은 소모임 멤버가 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
		}
		
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/group/leader/exile", method = RequestMethod.GET)
	@Override
	public ModelAndView exile(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> exileMap = new HashMap<String, Object>();
		exileMap.put("group_id", group_id);
		exileMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		boolean isGroupMemberResult = leaderService.isGroupMember(exileMap);
		
		if(isGroupMemberResult) {
			String msg = leaderService.exileMember(exileMap) + "님을 소모임에서 추방했습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
		} else {
			mav.addObject("data", new Message("해당 회원은 소모임 멤버가 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/group/leader/approve", method = RequestMethod.GET)
	@Override
	public ModelAndView approve(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> approveMap = new HashMap<String, Object>();
		approveMap.put("group_id", group_id);
		approveMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		boolean isWaitResult = leaderService.isWait(approveMap);
		
		if(isWaitResult) {
			
			String msg = leaderService.approveMember(approveMap) + "님의 소모임 가입을 승인하였습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}else {
			
			mav.addObject("data", new Message("해당 회원은 가입 대기 중이 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/group/leader/reject", method = RequestMethod.GET)
	@Override
	public ModelAndView reject(
			@RequestParam("group_id") int group_id,
			@RequestParam("target_id") String target_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> rejectMap = new HashMap<String, Object>();
		rejectMap.put("group_id", group_id);
		rejectMap.put("target_id", target_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		boolean isWaitResult = leaderService.isWait(rejectMap);
		
		if(isWaitResult) {
			
			String msg = leaderService.rejectMember(rejectMap) + "님의 소모임 가입을 거절하였습니다.";
			mav.addObject("data", new Message(msg, request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}else {
			
			mav.addObject("data", new Message("해당 회원은 가입 대기 중이 아닙니다.", request.getContextPath()+"/group/leader/memberinfo?group_id="+group_id));
			
		}

		return mav;	
	}
	
	
	@RequestMapping(value = "/group/leader/settingform", method = RequestMethod.GET)
	@Override
	public ModelAndView settingForm(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		GroupVO group = leaderService.getGroupInfo(group_id);
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("group", group);

		return mav;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/group/leader/modgroupsetting", method = RequestMethod.POST)
	@Override
	public ResponseEntity modGroupSetting(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> groupMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			groupMap.put(name, value);
		}
		
		String message = "";
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		GROUP_IMG_REPO = this.getClass().getResource("").getPath();
		GROUP_IMG_REPO = GROUP_IMG_REPO.substring(1, GROUP_IMG_REPO.indexOf(".metadata"));
		GROUP_IMG_REPO = GROUP_IMG_REPO.replace("/", "\\");
		GROUP_IMG_REPO += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\groupImages\\";
		
		String str_group_id = (String) groupMap.get("group_id");
		String originalFileName = (String) groupMap.get("originalFileName");
		String isDeleteImg = (String) groupMap.get("isDeleteImg");
		
		//logger.info("isDeleteImg={}", isDeleteImg);
		
		int currentMemNo = leaderService.getCurrentMaxMemNo(Integer.parseInt(str_group_id));
		int newMaxMemNo = Integer.parseInt((String) groupMap.get("max_memno"));
		
		if(currentMemNo > newMaxMemNo) {
			message = "<script>";
			message += "alert('현재 소모임 인원수보다 작은 값으로 소모임 최대 인원수를 설정할 수 없습니다.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/group/groupmain?group_id="+str_group_id+"';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
			return resEnt;
		}
		
		
		String group_img = upload(multipartRequest, isDeleteImg);

		try {

			if(group_img != null) {
				// 이미지 변경함 또는 삭제함
				File srcFile = new File(GROUP_IMG_REPO + "\\temp\\" + group_img);
				File destDir = new File(GROUP_IMG_REPO + "\\" + str_group_id);
				destDir.mkdirs();
				FileUtils.cleanDirectory(destDir);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				srcFile.delete();
			}else{
				// 이미지 그대로
				group_img = originalFileName;
			}

			groupMap.put("group_img", group_img);
			//logger.info("groupMap={}", groupMap.toString());
			leaderService.modGroupSetting(groupMap);
			
			message = "<script>";
			message += "alert('소모임 정보를 수정하였습니다.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/group/groupmain?group_id="+str_group_id+"';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

			
		} catch (Exception e) {
			e.printStackTrace();
			
			File srcFile = new File(GROUP_IMG_REPO + "\\temp\\" + group_img);
			srcFile.delete();
			message = "<script>";
			message += "alert('회원정보 수정에 실패하였습니다.')";
			message += "location.href='" + multipartRequest.getContextPath() + "/group/groupmain?group_id=" + str_group_id + "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		}

		return resEnt;
	}
	
	
	@RequestMapping(value = "/group/leader/delgroupform", method = RequestMethod.GET)
	@Override
	public ModelAndView delGroupForm(
			@RequestParam("group_id") int group_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}
	
	
	@RequestMapping(value = "/group/leader/delgroup", method = RequestMethod.POST)
	@Override
	public ModelAndView delGroup(
			@RequestParam("group_id") int group_id,
			@RequestParam("delString") String delString,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message");
		
		if(delString.equals("삭제하기")) {
			leaderService.delGroup(group_id);
			mav.addObject("data", new Message("소모임이 삭제되었습니다.", request.getContextPath()+"/index"));
			
		} else {
			
			mav.addObject("data", new Message("잘못 입력하셨습니다.", request.getContextPath()+"/group/leader/delgroupform?group_id="+group_id));
		}

		
		return mav;
	}
	
	
	private String upload(MultipartHttpServletRequest multipartRequest, String isDeleteImg) throws Exception {
		String imageFileName = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		if(isDeleteImg.equals("true")) {
			logger.info("이미지 삭제함");
			imageFileName = "sea.jpg";
			
			GROUP_IMG_DEFAULT = this.getClass().getResource("").getPath();
			GROUP_IMG_DEFAULT = GROUP_IMG_DEFAULT.substring(1, GROUP_IMG_DEFAULT.indexOf(".metadata"));
			GROUP_IMG_DEFAULT = GROUP_IMG_DEFAULT.replace("/", "\\");
			GROUP_IMG_DEFAULT += "nemo_spring_mvc\\src\\main\\webapp\\resources\\images\\sea.jpg";
			
			// 이미지 파일 업로드
			GROUP_IMG_REPO = this.getClass().getResource("").getPath();
			GROUP_IMG_REPO = GROUP_IMG_REPO.substring(1, GROUP_IMG_REPO.indexOf(".metadata"));
			GROUP_IMG_REPO = GROUP_IMG_REPO.replace("/", "\\");
			GROUP_IMG_REPO += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\groupImages\\";
			
			File srcFile = new File(GROUP_IMG_DEFAULT);
			File destDir = new File(GROUP_IMG_REPO + "\\temp");
			destDir.mkdir();
			FileUtils.copyFileToDirectory(srcFile, destDir, true);
			
			return imageFileName;

		}
		
		while (fileNames.hasNext()) {
			//logger.info("이미지 변경 또는 유지");
			String fileName = fileNames.next();
			//logger.info("fileName={}", fileName);
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			//logger.info("imageFileName={}", imageFileName);
			
			if(isDeleteImg.equals("false") && (imageFileName == null || imageFileName.equals("") || imageFileName.length() == 0)) {
				//logger.info("이미지 그대로");
				return null;
			}
			

			if (mFile.getSize() != 0) {
				mFile.transferTo(new File(GROUP_IMG_REPO + "\\temp\\" + imageFileName));
			}
		}

		return imageFileName;
	} // end of upload
	

}
