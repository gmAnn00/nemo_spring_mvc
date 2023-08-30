package com.springmvc.nemo.user.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.user.service.SignUpService;
import com.springmvc.nemo.user.vo.InterestsVO;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("joinController")
public class SignUpControllerImpl implements SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	private static String USER_IMG_REPO;
	private static String USER_IMG_DEFAULT;
	
	/** 어노테이션을 이용한 방법 **/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}  

	@Override
	@RequestMapping(value = "/signup/agree", method = RequestMethod.GET)
	public ModelAndView agree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@Override
	@RequestMapping(value = "/signup/joinform", method = RequestMethod.POST)
	public ModelAndView joinForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@Override
	@RequestMapping(value = "/signup/join", method = RequestMethod.POST)
	public ModelAndView join(
			@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		//System.out.println("birthdate=" + user.getBirthdate());
		
		signUpService.join(user);
		
		HttpSession session = request.getSession();
		session.setAttribute("user_id_temp", user.getUser_id());
		
		// 회원 이미지 자동 추가
		USER_IMG_REPO = this.getClass().getResource("").getPath();
		USER_IMG_REPO = USER_IMG_REPO.substring(1, USER_IMG_REPO.indexOf(".metadata"));
		USER_IMG_REPO = USER_IMG_REPO.replace("/", "\\");
		USER_IMG_REPO += "nemo_spring_mvc\\src\\main\\webapp\\WEB-INF\\views\\userImages\\";
		
		USER_IMG_DEFAULT = this.getClass().getResource("").getPath();
		USER_IMG_DEFAULT = USER_IMG_DEFAULT.substring(1, USER_IMG_DEFAULT.indexOf(".metadata"));
		USER_IMG_DEFAULT = USER_IMG_DEFAULT.replace("/", "\\");
		USER_IMG_DEFAULT += "nemo_spring_mvc\\src\\main\\webapp\\resources\\images\\pingoo.jpg";
		
		File srcFile = new File(USER_IMG_DEFAULT);
		File destDir = new File(USER_IMG_REPO + "\\" + user.getUser_id());
		destDir.mkdir();
		FileUtils.copyFileToDirectory(srcFile, destDir, false);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", new Message("회원가입이 완료되었습니다.", request.getContextPath()+"/signup/interestsform"));
		mav.setViewName("message");
		return mav;
	} // end of join

	@Override
	@RequestMapping(value = "/signup/interestsform", method = RequestMethod.GET)
	public ModelAndView interestsForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@Override
	@RequestMapping(value = "/signup/interests", method = RequestMethod.POST)
	public ModelAndView interests(@RequestParam("interests") String interests,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id_temp");
		session.removeAttribute("user_id_temp");
		
		System.out.println("interests="+interests);
		
		JsonArray jsonArray = new Gson().fromJson(interests, JsonArray.class);
		
		List<InterestsVO> interestsVOs = new ArrayList<InterestsVO>();
		for(JsonElement elem : jsonArray) {
			JsonObject interestObj = elem.getAsJsonObject();
			
			InterestsVO interestsVO = new InterestsVO();
			interestsVO.setUser_id(user_id);
			interestsVO.setMain_cate(interestObj.get("main_cate").getAsString());
			interestsVO.setSub_cate(interestObj.get("sub_cate").getAsString());
			
			interestsVOs.add(interestsVO);
			//System.out.println(interestsVO.toString());
		}
		
		signUpService.interests(interestsVOs);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", new Message("관심사를 추가하였습니다.", request.getContextPath()+"/login/loginform"));
		mav.setViewName("message");
		
		//mav.setViewName("redirect:/login/loginform");
		return mav;
	}
	
	

}
