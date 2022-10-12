package com.yoma.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;

import com.yoma.model.view.ResponseView;

@Component
public class ErrorHandling {
	
	public <T> ResponseView<T> errorHandling(Exception e,ResponseView<T> resp) {
		
		if(e instanceof SQLIntegrityConstraintViolationException) {
			resp.setStatusCode("201");
			resp.setMessage("Duplicate Error");
		}
		if(e instanceof JpaSystemException) {
			resp.setStatusCode("201");
			resp.setMessage("Transaction Fail");
		}
		if(e instanceof DataIntegrityViolationException) {
			resp.setStatusCode("201");
			resp.setMessage("Transaction Fail");
		}
		else{
			resp.setMessage(e.getMessage());
		}
		
		return resp;
	}
}
