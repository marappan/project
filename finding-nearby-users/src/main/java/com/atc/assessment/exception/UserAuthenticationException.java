package com.atc.assessment.exception;

public class UserAuthenticationException extends ATCProximityGenericException{
	private static final long serialVersionUID = 1L;

	public UserAuthenticationException() {
		
	}
	
	public UserAuthenticationException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
}
