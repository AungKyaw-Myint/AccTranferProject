package com.yoma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoma.model.Customer;
import com.yoma.model.Payment;
import com.yoma.model.view.AccountTransfer;
import com.yoma.model.view.ResponseView;
import com.yoma.service.PaymentService;

@RestController
@RequestMapping("payment")
public class PaymentController {
	
	@Autowired
	PaymentService payService;
	
	@PostMapping("/deposit")
	private ResponseView<Payment> saveDeposit(@RequestBody Payment payment){
		return payService.savePayment( payment, "deposit");
	}  
	
	@PostMapping("/withdraw")
	private ResponseView<Payment> saveWithdraw(@RequestBody Payment payment){ 
		return payService.savePayment( payment, "withdraw");
	}  
	
	@PostMapping("/transfer")
	private ResponseView<Payment> saveTransfer(@RequestBody AccountTransfer accTransfer){ 
		return payService.savePaymentTransfer(accTransfer);
	} 
	
	@PostMapping("/search")
	private ResponseView<List<Payment>> getPayment(@RequestBody Payment payment){
		return payService.getPayment(payment);
	}
}
