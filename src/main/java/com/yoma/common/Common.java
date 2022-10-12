package com.yoma.common;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Common {
	
	public String getTime() {
		Date dt = new Date();
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("kkmmss");
		
		return dateFormat.format(dt).toString();
	}
	
	public String getTranDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();       
		
		return dtf.format(now);
	}
	
	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();       
		
		return dtf.format(now);
	}

}
