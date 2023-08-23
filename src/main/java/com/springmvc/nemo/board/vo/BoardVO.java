package com.springmvc.nemo.board.vo;

import java.sql.Date;

public class BoardVO {

	private int article_no;
	private String user_id;
	private int group_id;
	private Date create_date;
	private String title;
	private String content;
	private String brackets;
	private int view_cnt;
	private int comment_cnt;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrackets() {
		return brackets;
	}

	public void setBrackets(String brackets) {
		this.brackets = brackets;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	@Override
	public String toString() {
		return "BoardVO [article_no=" + article_no + ", user_id=" + user_id + ", group_id=" + group_id
				+ ", create_date=" + create_date + ", title=" + title + ", content=" + content + ", brackets="
				+ brackets + ", view_cnt=" + view_cnt + ", comment_cnt=" + comment_cnt + "]";
	}
	
	
	
}
