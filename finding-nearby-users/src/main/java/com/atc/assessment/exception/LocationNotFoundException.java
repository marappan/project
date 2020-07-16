package com.atc.assessment.exception;

public class LocationNotFoundException extends ATCProximityGenericException{
	private static final long serialVersionUID = 1L;

	public LocationNotFoundException() {
		
	}
	
	public LocationNotFoundException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
}
