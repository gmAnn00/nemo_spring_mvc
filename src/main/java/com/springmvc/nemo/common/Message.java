package com.springmvc.nemo.common;

public class Message {
	String message = "";
	String href = "";

	public Message(String message, String href) {
		this.message = message;
		this.href = href;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	
}
