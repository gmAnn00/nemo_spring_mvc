package com.springmvc.nemo.schedule.vo;

public class AttendVO {
	
	private int attend_id;
	private int schedule_id;
	private String user_id;
	
	public AttendVO() {
		// TODO Auto-generated constructor stub
	}

	public int getAttend_id() {
		return attend_id;
	}

	public void setAttend_id(int attend_id) {
		this.attend_id = attend_id;
	}

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "AttendVO [attend_id=" + attend_id + ", schedule_id=" + schedule_id + ", user_id=" + user_id + "]";
	}
	
	
	

}
