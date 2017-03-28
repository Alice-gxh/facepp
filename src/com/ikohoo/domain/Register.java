package com.ikohoo.domain;

public class Register {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}
	private String id;
	private String user_id;
	private String photo_id;
	
	@Override
	public String toString() {
		return "Register [id=" + id + ", user_id=" + user_id + ", photo_id="
				+ photo_id + "]";
	}
	
	
}
