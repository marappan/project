package com.atc.assessment.exception;

public class ATCProximityGenericException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;

	public ATCProximityGenericException() {
		super();
	}

	public ATCProximityGenericException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
