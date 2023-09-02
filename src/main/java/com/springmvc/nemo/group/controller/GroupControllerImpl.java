package com.springmvc.nemo.group.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.group.service.GroupService;
import com.springmvc.nemo.group.vo.GroupVO;

@Controller("groupController")
public class GroupControllerImpl implements GroupController{
	
	private static final Logger logger = LoggerFactory.getLogger(GroupControllerImpl.class);
	
	private static String GROUP_IMG_REPO;
	private static String GROUP_IMG_DEFAULT;
	
	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/group/creategroupform", method = RequestMethod.GET)
	@Override
	public ModelAndView createGroupForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value = "/group/creategroup", method = RequestMethod.POST)
	@Override
	public ResponseEntity createGroup(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> groupMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		//logger.info("enum={}", enu.toString());
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			groupMap.put(name, value);
		}
		
		HttpSession session = multipartRequest.getSession();
		String user_id = (String) session.getAttribute("user_id");
		groupMap.put("group_leader", user_id);
		
		// 이미지 파일 업로드
		GROUP_IMG_REPO = this.getClass().getResource("").getPath();
		GROUP_IMG_REPO = GROUP_IMG_REPO.substring(1, GROUP_IMG_REPO.indexOf(".metadata"));
		GROUP_IMG_REPO = GROUP_IMG_REPO.replace("/", "\\");
		GROUP_IMG_REPO += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\groupImages\\";
		
		GROUP_IMG_DEFAULT = this.getClass().getResource("").getPath();
		GROUP_IMG_DEFAULT = GROUP_IMG_DEFAULT.substring(1, GROUP_IMG_DEFAULT.indexOf(".metadata"));
		GROUP_IMG_DEFAULT = GROUP_IMG_DEFAULT.replace("/", "\\");
		GROUP_IMG_DEFAULT += "nemo_spring_mvc\\src\\main\\webapp\\resources\\images\\sea.jpg";
		
		String message = "";
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		String group_img = upload(multipartRequest);
		groupMap.put("group_img", group_img);
		//logger.info("groupMap={}", groupMap.toString());
		
		try {
			
			String group_id = Integer.toString(groupService.createGroup(groupMap));
			File srcFile = new File(GROUP_IMG_REPO + "\\temp\\" + group_img);
			File destDir = new File(GROUP_IMG_REPO + "\\" + group_id);
			destDir.mkdirs();
			FileUtils.cleanDirectory(destDir);
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			srcFile.delete();
			
			message = "<script>";
			message += "alert('소모임을 생성하였습니다.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/group/groupmain?group_id=" + group_id + "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			File srcFile = new File(GROUP_IMG_REPO + "\\temp\\" + group_img);
			srcFile.delete();
			message = "<script>";
			message += "alert('소모임 생성에 실패하였습니다.')";
			message += "location.href='" + multipartRequest.getContextPath() + "/group/creategroupform';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		
		return resEnt;
	}
	
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String imageFileName = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while (fileNames.hasNext()) {
			//logger.info("이미지 업로드");
			String fileName = fileNames.next();
			//logger.info("fileName={}", fileName);
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			//logger.info("imageFileName={}", imageFileName);
			
			if(imageFileName == null || imageFileName.equals("") || imageFileName.length() == 0) {
				//logger.info("이미지 선택 안함");
				imageFileName = "sea.jpg";
				
				File srcFile = new File(GROUP_IMG_DEFAULT);
				File destDir = new File(GROUP_IMG_REPO + "\\temp");
				destDir.mkdir();
				FileUtils.copyFileToDirectory(srcFile, destDir, true);
				
				return imageFileName;
				
			}
			
			if (mFile.getSize() != 0) {

				mFile.transferTo(new File(GROUP_IMG_REPO + "\\temp\\" + imageFileName));
			}
		}

		return imageFileName;
	} // end of upload
}
