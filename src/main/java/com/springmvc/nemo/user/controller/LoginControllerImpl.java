package com.springmvc.nemo.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.nemo.common.Message;
import com.springmvc.nemo.common.file.GetKakaoImage;
import com.springmvc.nemo.user.service.LoginService;
import com.springmvc.nemo.user.vo.KakaoVO;
import com.springmvc.nemo.user.vo.UserVO;

@Controller("loginController")
public class LoginControllerImpl implements LoginController{
	private static final Logger logger = LoggerFactory.getLogger(LoginControllerImpl.class);
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login/loginform", method = RequestMethod.GET)
	@Override
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@RequestMapping(value = "/login/logintry", method = RequestMethod.POST)
	@Override
	public ModelAndView loginTry(@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		
		Boolean loginResult = loginService.loginTry(user);
		
		if(loginResult) {
			
			UserVO userVO = loginService.findUserById(user.getUser_id());
			session.setAttribute("user_id", userVO.getUser_id());
			session.setAttribute("nickname", userVO.getNickname());
			session.setAttribute("user_img", userVO.getUser_img());
			session.setAttribute("admin", userVO.getAdmin());
			session.setAttribute("sns", 0);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/index");
			return mav;
			
		}else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("data", new Message("회원 정보가 없습니다.", request.getContextPath()+"/login/loginform"));
			mav.setViewName("message");
			return mav;
		}

	}

	
	@RequestMapping(value = "/login/logout", method = RequestMethod.GET)
	@Override
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			
			String user_id = (String)session.getAttribute("user_id");
			int sns = (Integer) session.getAttribute("sns");
			
			if(sns == 1){
				loginService.kakaoLogout(user_id);
			}

			session.invalidate();
			
			
		}
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index");
		return mav;
		
	}

	@RequestMapping(value = "/login/findidform", method = RequestMethod.GET)
	@Override
	public ModelAndView findIdForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	
	@RequestMapping(value = "/login/findid", method = RequestMethod.POST)
	@Override
	public ModelAndView findId(@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userIdFind = loginService.findId(user);
		logger.info("userIdFind=" + userIdFind);
		
		ModelAndView mav = new ModelAndView();
		
		if(userIdFind != null) {
			String viewName = (String) request.getAttribute("viewName");
			
			mav.setViewName(viewName);
			mav.addObject("userIdFind", userIdFind);

		} else {
			mav.addObject("data", new Message("회원 정보가 없습니다.", request.getContextPath()+"/login/findidform"));
			mav.setViewName("message");
		}
		
		return mav;
	}

	@RequestMapping(value = "/login/resetpasswordcheckform", method = RequestMethod.GET)
	@Override
	public ModelAndView resetPasswordCheckForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@RequestMapping(value = "/login/resetpasswordcheck", method = RequestMethod.POST)
	@Override
	public ModelAndView resetPasswordCheck(@ModelAttribute("user") UserVO user,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("userIdTemp", user.getUser_id());
		
		Boolean result = loginService.resetPasswordCheck(user);
		
		ModelAndView mav = new ModelAndView();
		if(result) {
			
			mav.setViewName("redirect:/login/resetpasswordform");

		}else {
			mav.addObject("data", new Message("회원 정보가 없습니다.", request.getContextPath()+"/login/resetpasswordcheckform"));
			mav.setViewName("message");
		}
		return mav;
		
	}

	@RequestMapping(value = "/login/resetpasswordform", method = RequestMethod.GET)
	@Override
	public ModelAndView resetPasswordForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		return mav;
	}

	@RequestMapping(value = "/login/resetpassword", method = RequestMethod.POST)
	@Override
	public ModelAndView resetPassword(@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
	
		String user_id = (String) session.getAttribute("userIdTemp");
		session.removeAttribute("userIdTemp");
		
		UserVO userVO = new UserVO();
		userVO.setUser_id(user_id);
		userVO.setPassword(password);
		
		loginService.resetPassword(userVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", new Message("비밀번호가 재설정 되었습니다.", request.getContextPath()+"/login/loginform"));
		mav.setViewName("message");
		return mav;
	}
	
	
	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	@Override
	public String oauth(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "error_description", required = false) String error_description,
			@RequestParam(value = "state", required = false) String state,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);

		//logger.info("code={}", code);
		//logger.info("error={}", error);
		//logger.info("error_description={}", error_description);
		//logger.info("state={}", state);
		
		String access_token = loginService.getKakaoAccessToken(code);
		KakaoVO kakaoVO = loginService.getKakaoUserInfo(access_token);
		
		logger.info("id={}", kakaoVO.getUser_id());
		
		
		UserVO userVO = loginService.kakaoLogin(kakaoVO);
		
		logger.info("kakaoVO={}",kakaoVO.toString());
		logger.info("userVO={}", userVO.toString());
		
		GetKakaoImage getKakaoImage = new GetKakaoImage();
		getKakaoImage.getImage(kakaoVO, userVO.getUser_img());
		
		session.setAttribute("user_id", userVO.getUser_id());
		session.setAttribute("nickname", userVO.getNickname());
		session.setAttribute("user_img", userVO.getUser_img());
		session.setAttribute("admin", userVO.getAdmin());
		session.setAttribute("sns", 1);
		
		return "redirect:/index";
	}


}
