package com.yoma.exception;

public class TransactionFailException extends Exception{
	
	private String statusCode="111";
	private String message="Transaction Fail, Please Try Aagin!!";
	
	public String getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}

}
