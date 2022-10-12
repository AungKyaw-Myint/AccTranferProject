package com.yoma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoma.exception.AlreadyDeletedException;
import com.yoma.exception.ErrorHandling;
import com.yoma.exception.NoDataFoundException;
import com.yoma.model.Account;
import com.yoma.model.view.ResponseView;
import com.yoma.repo.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	ErrorHandling errorHandle;

	@Override
	public ResponseView<List<Account>> getAllAccountList() {
		
		ResponseView<List<Account>> resp=new ResponseView<>();
		List<Account> accList=new ArrayList<Account>();
		try {
			accList=accRepo.findAll();
			resp.setStatusCode("200");
			resp.setMessage("Account List");
			resp.setModel(accList);
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		
		return resp;
	}

	@Override
	public ResponseView<Account> saveAccount(Account account) {
		ResponseView<Account> resp=new ResponseView<>();
		try {
			//update Data
			if(account.getAcc_id()!=0) {
				accRepo.save(account);												//save
				Account acc=accRepo.findByAccountId(account.getAcc_id());			//find by ID
				
				
				resp.setStatusCode("200");
				resp.setMessage("Customer Data Update Successfully!");
				resp.setModel((Account) acc);
			}else {
				accRepo.save(account);
				resp.setStatusCode("200");
				resp.setMessage("Customer Data Save Successfully!");
				
			}
			
			//throw new AmountInsufficientException();
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		return resp;
	}

	

	@Override
	@Transactional
	public ResponseView<Account> deleteAccount(int accountId) {
		ResponseView<Account> resp=new ResponseView<>();
		Account acc=new Account();
		try {
			acc=accRepo.findByAccountId(accountId);
			if(acc == null) {
				throw new NoDataFoundException();
			}
			if(acc.getDel_flag()!=0) {
				throw new AlreadyDeletedException();
			}
			
			accRepo.deleteAccount(accountId);
			resp.setStatusCode("200");
			resp.setMessage("Successfully Deleted!");
		} catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		return resp;
	}

	@Override
	public ResponseView<Account> searchAccountNo(Account account) {
		ResponseView<Account> resp=new ResponseView<>();
		Account acc=new Account();
		try {
			//System.out.println("acc no |"+account.getAccount());
			acc=accRepo.searchAccountNo(account.getAcc_no());
			
			if(acc == null) {
				throw new NoDataFoundException();
			}
			if(acc.getDel_flag()!=0) {
				throw new AlreadyDeletedException();
			}
			
			resp.setModel(acc);
			resp.setStatusCode("200");
			resp.setMessage("Success");
		} catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		return resp;
	}

	@Override
	public ResponseView<Account> getAccountById(int id) {
		ResponseView<Account> resp=new ResponseView<>();
		Account acc=new Account();
		try {
			acc=accRepo.findByAccountId(id);
			
			if(acc == null) {
				throw new NoDataFoundException();
			}
			System.out.println(acc.getDel_flag());
			if(acc.getDel_flag()!=0) {
				throw new AlreadyDeletedException();
			}
			
			resp.setModel(acc);
			resp.setStatusCode("200");
			resp.setMessage("Success");
		} catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		return resp;
	}

	@Override
	public List<Account> getAccountByCustomer(int id) {
		
		return accRepo.getAccountByCustomer(id);
	}
}
