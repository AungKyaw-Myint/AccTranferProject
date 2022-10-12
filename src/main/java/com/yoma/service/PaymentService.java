package com.yoma.service;

import java.util.List;

import com.yoma.model.Payment;
import com.yoma.model.view.AccountTransfer;
import com.yoma.model.view.ResponseView;

public interface PaymentService {
	
	ResponseView<Payment> savePayment(Payment account,String withdraw_deposit);
	ResponseView<Payment> savePaymentTransfer(AccountTransfer accTransfer);
	ResponseView<List<Payment>> getPayment(Payment payment);
}
