package com.springmvc.nemo.report.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;


public class UserReportVO {

	private int report_id;
	private String reporter_id;
	private String accused_id;
	private Date report_date;
	private String report_content;

	public UserReportVO() {
		// TODO Auto-generated constructor stub
	}

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public String getReporter_id() {
		return reporter_id;
	}

	public void setReporter_id(String reporter_id) {
		this.reporter_id = reporter_id;
	}

	public String getAccused_id() {
		return accused_id;
	}

	public void setAccused_id(String accused_id) {
		this.accused_id = accused_id;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	@Override
	public String toString() {
		return "UserReportVO [report_id=" + report_id + ", reporter_id=" + reporter_id + ", accused_id=" + accused_id
				+ ", report_date=" + report_date + ", report_content=" + report_content + "]";
	}

	

}
