package com.atc.assessment.exception;

public class UserNotFoundException extends ATCProximityGenericException{
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		
	}

	public UserNotFoundException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
