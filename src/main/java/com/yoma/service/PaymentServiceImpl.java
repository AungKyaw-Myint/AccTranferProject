package com.yoma.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoma.common.Common;
import com.yoma.exception.AccountFreezeException;
import com.yoma.exception.AlreadyDeletedException;
import com.yoma.exception.AmountInsufficientException;
import com.yoma.exception.ErrorHandling;
import com.yoma.exception.NoDataFoundException;
import com.yoma.model.Account;
import com.yoma.model.Payment;
import com.yoma.model.Trans;
import com.yoma.model.view.AccountTransfer;
import com.yoma.model.view.ResponseView;
import com.yoma.repo.AccountRepo;
import com.yoma.repo.PaymentRepo;
import com.yoma.repo.TransactionRepo;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	Common common;
	
	@Autowired
	PaymentRepo payRepo;
	
	@Autowired
	ErrorHandling errorHandle;

	@Autowired
	TransactionRepo tranRepo;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseView<Payment> savePayment(Payment payment,String withdraw_deposit) {
		ResponseView<Payment> resp=new ResponseView<>();
		Account acc=new Account();
		Trans tran=new Trans();
		
		String paymentId= 'P'+common.getTime();
		String tranDate = common.getTranDate();
		
		try {
			
			acc=accRepo.searchAccountNo(payment.getAccount_no());
			
			if(acc == null) {
				throw new NoDataFoundException();
			}
			if(acc.getDel_flag()!=0) {
				throw new AccountFreezeException();
			}
			//for cash withdraw
			if(withdraw_deposit.equals("withdraw")) {
				double checkAmount=acc.getBalance()-payment.getAmount();
				
				if(checkAmount<0) {
					throw new AmountInsufficientException();
				}
				//update Amount
				acc.setBalance(acc.getBalance()-payment.getAmount());			
				
				//Payment table
				payment.setPayment_id(paymentId);
				payment.setDebit_credit("Dr");
				payment.setType("C");
				payment.setPay_date(common.getDate());
				
				//Tran table
				tran.setTran_date(tranDate);
				tran.setTran_amount(payment.getAmount());
				tran.setFrom_acc("Office Acc");
				tran.setTo_acc(payment.getAccount_no());
				tran.setPayment_id(paymentId);
				
				accRepo.save(acc);							//update amount for Account_Table
				payRepo.save(payment);						//Save payment for Save_Table
				tranRepo.save(tran);						//Save Transaction for Tran_table
				
				resp.setStatusCode("200");
				resp.setMessage("Withdraw Successfully!");
				
			}//for cash Deposit
			else if(withdraw_deposit.equals("deposit")) {
				acc.setBalance(acc.getBalance()+payment.getAmount());
				
				
				payment.setPayment_id(paymentId);
				payment.setDebit_credit("Cr");
				payment.setType("C");
				payment.setPay_date(common.getDate());
				
				//Tran table
				tran.setTran_date(tranDate);
				tran.setTran_amount(payment.getAmount());
				tran.setFrom_acc(payment.getAccount_no());
				tran.setTo_acc("Office Acc");
				tran.setPayment_id(paymentId);
				
				accRepo.save(acc);							//update amount for Account_Table
				payRepo.save(payment);						//Save payment for Save_Table
				tranRepo.save(tran);						//Save Transaction for Tran_table
				
				resp.setStatusCode("200");
				resp.setMessage("Deposit Successfully!");
			}
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
			System.out.println("Error |"+e);
		}
		return resp;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseView<Payment> savePaymentTransfer(AccountTransfer accTransfer) {
		ResponseView<Payment> resp=new ResponseView<>();
		Account fromAcc=new Account();
		Account toAcc=new Account();
		Trans tran=new Trans();
		Payment payment=new Payment();
		
		String paymentId= 'P'+common.getTime();
		String tranDate = common.getTranDate();
		
		try {
			fromAcc=accChecking(accTransfer.getFrom_acc(),accTransfer.getAmount());
			toAcc=accChecking(accTransfer.getTo_acc(),0);
			
			//update Amount
			fromAcc.setBalance(fromAcc.getBalance()-accTransfer.getAmount());		
			//Payment table
			payment.setAccount_no(accTransfer.getFrom_acc());
			System.out.println(paymentId);
			payment.setPayment_id(paymentId);
			payment.setSerial_no(1);
			payment.setDebit_credit("Dr");
			payment.setType("T");
			payment.setAmount(accTransfer.getAmount());
			payment.setPay_date(common.getDate());
			
			accRepo.save(fromAcc);							//update amount for Account_Table
			payRepo.save(payment);						//Save payment for Save_Table
			
			
			payment=new Payment();
			
			//update Amount
			toAcc.setBalance(toAcc.getBalance()+accTransfer.getAmount());		
			//Payment table
			payment.setAccount_no(accTransfer.getTo_acc());
			System.out.println(paymentId);
			payment.setPayment_id(paymentId);
			payment.setSerial_no(2);
			payment.setDebit_credit("Cr");
			payment.setType("T");
			payment.setAmount(accTransfer.getAmount());
			payment.setPay_date(common.getDate());
			
			accRepo.save(toAcc);							//update amount for Account_Table
			payRepo.save(payment);						//Save payment for Save_Table
			
			//Tran table
			tran.setTran_date(tranDate);
			tran.setTran_amount(accTransfer.getAmount());
			tran.setFrom_acc(accTransfer.getFrom_acc());
			tran.setTo_acc(accTransfer.getTo_acc());
			tran.setPayment_id(paymentId);
			
			//accRepo.save(acc);							//update amount for Account_Table
			//payRepo.save(payment);						//Save payment for Save_Table
			tranRepo.save(tran);						//Save Transaction for Tran_table
			
			resp.setStatusCode("200");
			resp.setMessage("Transfer Successfully!");
			
			
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
			System.out.println("Error |"+e);
		}
		return resp;
		
	}
	
	public Account accChecking(String accId,double amount) throws Exception{
		Account acc=new Account();
			
		acc=accRepo.searchAccountNo(accId);
		if(acc == null) {
			throw new NoDataFoundException();
		}
		if(acc.getDel_flag()!=0) {
			throw new AccountFreezeException();
		}
		
		double checkAmount=acc.getBalance()-amount;
		if(checkAmount<0) {
			throw new AmountInsufficientException();
		}
		
		return acc;
	}

	@Override
	public ResponseView<List<Payment>> getPayment(Payment payment) {
		ResponseView<List<Payment>> resp=new ResponseView<>();
		List<Payment> payList=new ArrayList<Payment>();
		try {
			if(payment.getAccount_no() == null) {
				payList=payRepo.findAll();
			}else {
				payList=payRepo.findPaymentAccount(payment.getAccount_no());
				
				if(payList == null) {
					throw new NoDataFoundException();
				}
			}
			resp.setStatusCode("200");
			resp.setMessage("Account Payment List");
			resp.setModel(payList);
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		
		return resp;
	}

}
