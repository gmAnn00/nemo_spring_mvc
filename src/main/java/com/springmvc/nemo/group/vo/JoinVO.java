package com.springmvc.nemo.group.vo;

import java.sql.Date;

public class JoinVO {
	
	private int group_id;
	private String user_id;
	private Date join_date;
	private int cancel;
	
	public JoinVO() {
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

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	@Override
	public String toString() {
		return "JoinVO [group_id=" + group_id + ", user_id=" + user_id + ", join_date=" + join_date + ", cancel="
				+ cancel + "]";
	}
	
	

}
