package com.yoma.exception;

public class AlreadyDeletedException extends Exception {
	
	private String statusCode="111";
	private String message="Data Information Already Deleted!";
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
