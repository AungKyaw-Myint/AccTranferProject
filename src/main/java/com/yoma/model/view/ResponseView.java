package com.yoma.model.view;

import ch.qos.logback.core.subst.Token.Type;

public class ResponseView <T>{
	
	private String statusCode;
	private String message;
	private T model;
	
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
	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}
	
	

}
