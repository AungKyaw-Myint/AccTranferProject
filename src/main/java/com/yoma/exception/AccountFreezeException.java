package com.yoma.exception;

public class AccountFreezeException extends Exception {

	private String statusCode="111";
	private String message="Your Account was freezed!";
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
