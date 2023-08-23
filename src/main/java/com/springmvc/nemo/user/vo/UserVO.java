package com.springmvc.nemo.user.vo;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


public class UserVO {

	private String user_id;
	private String password;
	private String user_name;
	private String nickname;
	private String zipcode;
	private String user_addr1;
	private String user_addr2;
	private String phone;
	private String email;
	private Date join_date;
	private Date birthdate;
	private String user_img;
	private int report_cnt;
	private String session_id;
	private int sns_login;
	private int admin;
	private int cancel;

	public UserVO() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getUser_addr1() {
		return user_addr1;
	}

	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}

	public String getUser_addr2() {
		return user_addr2;
	}

	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
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

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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

	public int getReport_cnt() {
		return report_cnt;
	}

	public void setReport_cnt(int report_cnt) {
		this.report_cnt = report_cnt;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public int getSns_login() {
		return sns_login;
	}

	public void setSns_login(int sns_login) {
		this.sns_login = sns_login;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", password=" + password + ", user_name=" + user_name + ", nickname="
				+ nickname + ", zipcode=" + zipcode + ", user_addr1=" + user_addr1 + ", user_addr2=" + user_addr2
				+ ", phone=" + phone + ", email=" + email + ", join_date=" + join_date + ", birthdate=" + birthdate
				+ ", user_img=" + user_img + ", report_cnt=" + report_cnt + ", session_id=" + session_id
				+ ", sns_login=" + sns_login + ", admin=" + admin + ", cancel=" + cancel + "]";
	}

}
