package com.springmvc.nemo.board;

import java.sql.Date;

public class CommentVO {

	private int comment_no;
	private int article_no;
	private String user_id;
	private Date create_date;
	private String content;
	private int parent_no;
	
	public CommentVO() {
		// TODO Auto-generated constructor stub
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
