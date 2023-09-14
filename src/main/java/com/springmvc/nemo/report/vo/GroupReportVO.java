package com.springmvc.nemo.report.vo;

import java.sql.Date;

public class GroupReportVO {
	
	private int report_id;
	private int group_id;
	private String reporter_id;
	private Date report_date;
	private String report_content;
	
	public GroupReportVO() {
		// TODO Auto-generated constructor stub
	}

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getReporter_id() {
		return reporter_id;
	}

	public void setReporter_id(String reporter_id) {
		this.reporter_id = reporter_id;
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
		return "GroupReportVO [report_id=" + report_id + ", group_id=" + group_id + ", reporter_id=" + reporter_id
				+ ", report_date=" + report_date + ", report_content=" + report_content + "]";
	}

	
	
	

}
