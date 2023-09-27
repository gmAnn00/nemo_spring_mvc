package com.springmvc.nemo.board.vo;

import java.util.Arrays;
import java.util.Date;

public class BoardVO {

	private int article_no;
	private String user_id;
	private String nickname;
	private String user_img;
	private int group_id;
	private String group_name;
	private Date create_date;
	private String title;
	private String content;
	private String brackets;
	private int view_cnt;
	private int comment_cnt;
	private boolean isImgExist;
	private String[] imageName;
	
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

	public boolean getIsImgExist() {
		return isImgExist;
	}

	public void setIsImgExist(boolean isImgExist) {
		this.isImgExist = isImgExist;
	}

	public String[] getImageName() {
		return imageName;
	}

	public void setImageName(String[] imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "BoardVO [article_no=" + article_no + ", user_id=" + user_id + ", nickname=" + nickname + ", user_img="
				+ user_img + ", group_id=" + group_id + ", create_date=" + create_date + ", title=" + title
				+ ", content=" + content + ", brackets=" + brackets + ", view_cnt=" + view_cnt + ", comment_cnt="
				+ comment_cnt + ", isImgExist=" + isImgExist + ", imageName=" + Arrays.toString(imageName) + "]";
	}



	
}
