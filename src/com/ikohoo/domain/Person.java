package com.ikohoo.domain;

import java.util.Date;

public class Person {

	private String id;
	private String name;
	private String password;
	private String gender;
	private String nickname;
	private Date birthday;
	private String idcard;
	private String cellphone;
	private String email;
	private String createTm;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirthday() {
		return birthday;
		//return DateFormat.parse(new SimpleDateFormat("YYYYMMddHHmmss").format(birthday));
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreateTm() {
		return createTm; // new SimpleDateFormat("YYYY-MM-dd").format(new Date());
	}
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	
	
	
}
