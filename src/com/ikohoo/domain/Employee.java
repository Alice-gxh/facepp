package com.ikohoo.domain;

import java.io.Serializable;

public class Employee extends Person implements Serializable  {

	@Override
	public String toString() {
		return "Employee [role=" + role + ", state=" + state + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getPassword()="
				+ getPassword() + ", getGender()=" + getGender()
				+ ", getNickname()=" + getNickname() + ", getBirthday()="
				+ getBirthday() + ", getIdcard()=" + getIdcard()
				+ ", getCellphone()=" + getCellphone() + ", getEmail()="
				+ getEmail() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;
	private int state;
	private String dept;

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

}
