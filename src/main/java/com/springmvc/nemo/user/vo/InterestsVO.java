package com.springmvc.nemo.user.vo;

import org.springframework.stereotype.Component;


public class InterestsVO {

	private String user_id;
	private String main_cate;
	private String sub_cate;

	public InterestsVO() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMain_cate() {
		return main_cate;
	}

	public void setMain_cate(String main_cate) {
		this.main_cate = main_cate;
	}

	public String getSub_cate() {
		return sub_cate;
	}

	public void setSub_cate(String sub_cate) {
		this.sub_cate = sub_cate;
	}

	@Override
	public String toString() {
		return "InterestsVO [user_id=" + user_id + ", main_cate=" + main_cate + ", sub_cate=" + sub_cate + "]";
	}

	
	
}
