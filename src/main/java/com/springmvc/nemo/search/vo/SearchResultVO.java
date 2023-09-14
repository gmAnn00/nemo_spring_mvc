package com.springmvc.nemo.search.vo;

import java.sql.Date;


public class SearchResultVO {
	
	private int group_id;
	private String group_name;
	private String group_leader;
	private int max_memno;
	private int current_memno;
	private String group_zipcode;
	private String group_addr1;
	private String group_addr2;
	private Date create_date;
	private String group_desc;
	private int permission;
	private String main_cate;
	private String sub_cate;
	private String group_img;
	private int bookmark_no;
	private int report_cnt;
	private boolean isBookmark;
	
	public SearchResultVO() {
		// TODO Auto-generated constructor stub
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_leader() {
		return group_leader;
	}

	public void setGroup_leader(String group_leader) {
		this.group_leader = group_leader;
	}

	public int getMax_memno() {
		return max_memno;
	}

	public void setMax_memno(int max_memno) {
		this.max_memno = max_memno;
	}

	public int getCurrent_memno() {
		return current_memno;
	}

	public void setCurrent_memno(int current_memno) {
		this.current_memno = current_memno;
	}

	public String getGroup_zipcode() {
		return group_zipcode;
	}

	public void setGroup_zipcode(String group_zipcode) {
		this.group_zipcode = group_zipcode;
	}

	public String getGroup_addr1() {
		return group_addr1;
	}

	public void setGroup_addr1(String group_addr1) {
		this.group_addr1 = group_addr1;
	}

	public String getGroup_addr2() {
		return group_addr2;
	}

	public void setGroup_addr2(String group_addr2) {
		this.group_addr2 = group_addr2;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getGroup_desc() {
		return group_desc;
	}

	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
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

	public String getGroup_img() {
		return group_img;
	}

	public void setGroup_img(String group_img) {
		this.group_img = group_img;
	}

	public int getBookmark_no() {
		return bookmark_no;
	}

	public void setBookmark_no(int bookmark_no) {
		this.bookmark_no = bookmark_no;
	}

	public int getReport_cnt() {
		return report_cnt;
	}

	public void setReport_cnt(int report_cnt) {
		this.report_cnt = report_cnt;
	}

	public boolean getIsBookmark() {
		return this.isBookmark;
	}
	
	public void setIsBookmark(boolean isBookmark) {
		this.isBookmark = isBookmark;
	}

	@Override
	public String toString() {
		return "SearchResultVO [group_id=" + group_id + ", group_name=" + group_name + ", group_leader=" + group_leader
				+ ", max_memno=" + max_memno + ", current_memno=" + current_memno + ", group_zipcode=" + group_zipcode
				+ ", group_addr1=" + group_addr1 + ", group_addr2=" + group_addr2 + ", create_date=" + create_date
				+ ", group_desc=" + group_desc + ", permission=" + permission + ", main_cate=" + main_cate
				+ ", sub_cate=" + sub_cate + ", group_img=" + group_img + ", bookmark_no=" + bookmark_no
				+ ", report_cnt=" + report_cnt + ", isBookmark=" + isBookmark + "]";
	}



	
	

}
