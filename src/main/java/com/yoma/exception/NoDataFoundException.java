package com.yoma.exception;

public class NoDataFoundException extends Exception{
	
	private String statusCode="111";
	private String message="No Data Found";
	
	public String getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
	
	

}
