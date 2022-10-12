package com.yoma.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "transaction")
public class Trans {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tran_id", nullable = false)
	private int id;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String tran_date;
	private double tran_amount;
	private String from_acc;
	private String to_acc;
	private String payment_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTran_date() {
		return tran_date;
	}
	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}
	public double getTran_amount() {
		return tran_amount;
	}
	public void setTran_amount(double tran_amount) {
		this.tran_amount = tran_amount;
	}
	public String getFrom_acc() {
		return from_acc;
	}
	public void setFrom_acc(String from_acc) {
		this.from_acc = from_acc;
	}
	public String getTo_acc() {
		return to_acc;
	}
	public void setTo_acc(String to_acc) {
		this.to_acc = to_acc;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}	
}
