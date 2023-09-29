package com.springmvc.nemo.qna.vo;

import java.util.Arrays;
import java.util.Date;

public class QnaVO {

	private int LVL;
	private int qna_no;
	private String user_id;
	private String nickname;
	private String user_img;
	private int parent_no;
	private String title;
	private String content;
	private Date create_date;
	private boolean isImgExist;
	private String[] imageName;

	public QnaVO() {
		// TODO Auto-generated constructor stub
	}

	public int getLVL() {
		return LVL;
	}

	public void setLVL(int LVL) {
		this.LVL = LVL;
	}

	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
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

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
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
		return "QnaVO [level=" + LVL + ", qna_no=" + qna_no + ", user_id=" + user_id + ", nickname=" + nickname
				+ ", user_img=" + user_img + ", parent_no=" + parent_no + ", title=" + title + ", content=" + content
				+ ", create_date=" + create_date + ", isImgExist=" + isImgExist + ", imageName="
				+ Arrays.toString(imageName) + "]";
	}

	

}
