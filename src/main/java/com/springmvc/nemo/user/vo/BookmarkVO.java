package com.springmvc.nemo.user.vo;

public class BookmarkVO {

	private String user_id;
	private int group_id;
	
	public BookmarkVO() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	@Override
	public String toString() {
		return "BookmarkVO [user_id=" + user_id + ", group_id=" + group_id + "]";
	}
	
	
	
}
