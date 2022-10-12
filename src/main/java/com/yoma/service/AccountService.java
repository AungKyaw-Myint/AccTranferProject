package com.yoma.service;

import java.util.List;

import com.yoma.model.Account;
import com.yoma.model.view.ResponseView;

public interface AccountService{
	
	ResponseView<List<Account>> getAllAccountList();
	ResponseView<Account> saveAccount(Account account);
	ResponseView<Account> searchAccountNo(Account account);
	ResponseView<Account> deleteAccount(int accountId);
	ResponseView<Account> getAccountById(int id);
	List<Account> getAccountByCustomer(int id);

}
