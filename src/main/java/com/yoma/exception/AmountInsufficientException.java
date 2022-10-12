package com.yoma.exception;

public class AmountInsufficientException extends Exception{
	
	private String statusCode="111";
	private String message="Amount Insufficient Error";
	
	public String getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
	
	
}
