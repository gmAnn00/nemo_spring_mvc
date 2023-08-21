package com.springmvc.nemo.user.vo;

import java.sql.Date;

public class KakaoVO {
	
	private String user_id;
	private String kakao_mail;
	private String name;
	private Date birthdate;
	private String phone;
	
	public KakaoVO() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getKakao_mail() {
		return kakao_mail;
	}

	public void setKakao_mail(String kakao_mail) {
		this.kakao_mail = kakao_mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "KakaoVO [user_id=" + user_id + ", kakao_mail=" + kakao_mail + ", name=" + name + ", birthdate="
				+ birthdate + ", phone=" + phone + "]";
	}
	
	
	

}
