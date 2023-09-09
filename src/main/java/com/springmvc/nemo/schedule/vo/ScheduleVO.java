package com.springmvc.nemo.schedule.vo;

import java.sql.Timestamp;
import java.util.Date;

public class ScheduleVO {

	private int schedule_id;
	//private Timestamp schedule_date;
	private Date schedule_date;
	private int group_id;
	private String user_id;
	private String schedule_title;
	private String schedule_content;
	private String location;
	private int attendee_no;
	
	public ScheduleVO() {
		// TODO Auto-generated constructor stub
	}

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	
	/*
	public Timestamp getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(Timestamp schedule_date) {
		this.schedule_date = schedule_date;
	}
	*/
	
	public Date getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(Date schedule_date) {
		this.schedule_date = schedule_date;
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

	public String getSchedule_title() {
		return schedule_title;
	}

	public void setSchedule_title(String schedule_title) {
		this.schedule_title = schedule_title;
	}

	

	public String getSchedule_content() {
		return schedule_content;
	}

	public void setSchedule_content(String schedule_content) {
		this.schedule_content = schedule_content;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAttendee_no() {
		return attendee_no;
	}

	public void setAttendee_no(int attendee_no) {
		this.attendee_no = attendee_no;
	}

	@Override
	public String toString() {
		return "ScheduleVO [schedule_id=" + schedule_id + ", schedule_date=" + schedule_date + ", group_id=" + group_id
				+ ", user_id=" + user_id + ", schedule_title=" + schedule_title + ", schedule_content="
				+ schedule_content + ", location=" + location + ", attendee_no=" + attendee_no + "]";
	}

	
	
}
