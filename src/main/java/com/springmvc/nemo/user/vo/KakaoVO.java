package com.springmvc.nemo.user.vo;

public class KakaoVO {

	private String user_id;
	private String kakao_mail;
	private String nickname;
	private String kakao_img;
	private String access_token;

	public KakaoVO() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getKakao_mail() {
		return kakao_mail;
	}

	public void setKakao_mail(String kakao_mail) {
		this.kakao_mail = kakao_mail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getKakao_img() {
		return kakao_img;
	}

	public void setKakao_img(String kakao_img) {
		this.kakao_img = kakao_img;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@Override
	public String toString() {
		return "KakaoVO [user_id=" + user_id + ", kakao_mail=" + kakao_mail + ", nickname=" + nickname + ", kakao_img="
				+ kakao_img + ", access_token=" + access_token + "]";
	}

}
