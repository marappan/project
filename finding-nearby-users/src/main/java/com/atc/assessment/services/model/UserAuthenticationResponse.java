package com.atc.assessment.services.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAuthenticationResponse implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty(value="AuthToken")
	private String jwToken;

	public UserAuthenticationResponse(String jwToken) {
		this.jwToken = jwToken;
	}

	public String getJwToken() {
		return jwToken;
	}

	public void setJwToken(String jwToken) {
		this.jwToken = jwToken;
	}
}
