package com.springmvc.nemo.mypage.vo;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class MyProfileVO {
	
	private String user_id;
	//private String password;
	private String user_name;
	private String nickname;
	//private String zipcode;
	private String user_addr1;
	//private String user_addr2;
	private String phone;
	private String email;
	//private String join_date;
	private String birthdate;
	private String user_img;
	//private int report_cnt;
	//private String session_id;
	//private int sns_login;
	//private int admin;
	private int cancel;
	
	public MyProfileVO() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUser_addr1() {
		return user_addr1;
	}

	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getUser_img() {
		try {
			if (user_img != null && user_img.length() != 0) {
				user_img = URLDecoder.decode(user_img, "utf-8");
			}
		} catch (Exception e) {
			System.out.println("getUser_img error");
		}
		return user_img;
	}

	public void setUser_img(String user_img) {
		try {
			if (user_img != null && user_img.length() != 0) {
				this.user_img = URLEncoder.encode(user_img, "utf-8");
			}
		} catch (Exception e) {
			System.out.println("setUser_img error");
			e.printStackTrace();
		}
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	@Override
	public String toString() {
		return "MyProfileVO [user_id=" + user_id + ", user_name=" + user_name + ", nickname=" + nickname
				+ ", user_addr1=" + user_addr1 + ", phone=" + phone + ", email=" + email + ", birthdate=" + birthdate
				+ ", user_img=" + user_img + ", cancel=" + cancel + "]";
	}
	
	

}
