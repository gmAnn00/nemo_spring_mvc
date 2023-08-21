package com.springmvc.nemo.group.vo;

public class WaitListVO {
	
	private int group_id;
	private String user_id;
	
	public WaitListVO() {
		// TODO Auto-generated constructor stub
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "WaitListVO [group_id=" + group_id + ", user_id=" + user_id + "]";
	}
	
	

}
