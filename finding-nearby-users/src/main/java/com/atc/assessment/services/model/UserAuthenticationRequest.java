package com.atc.assessment.services.model;

import java.io.Serializable;

public class UserAuthenticationRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public UserAuthenticationRequest() {

	}

	public UserAuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
