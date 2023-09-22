package com.springmvc.nemo.board.vo;

import java.sql.Date;

public class CommentVO {

	private int level;
	private int comment_no;
	private int article_no;
	private String user_id;
	private String nickname;
	private String user_img;
	private Date create_date;
	private String content;
	private int parent_no;

	public CommentVO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}

	@Override
	public String toString() {
		return "CommentVO [comment_no=" + comment_no + ", article_no=" + article_no + ", user_id=" + user_id
				+ ", create_date=" + create_date + ", content=" + content + ", parent_no=" + parent_no + "]";
	}

}
