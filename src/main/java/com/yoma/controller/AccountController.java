package com.yoma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoma.model.Account;
import com.yoma.model.view.ResponseView;
import com.yoma.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accService;
	
	
	@GetMapping("/account")
	public ResponseView<List<Account>> getAccountInfo(){
		return accService.getAllAccountList();
	}
	
	
	@PostMapping("/search_account")  
	private ResponseView<Account> searchAccountNo(@RequestBody Account acc){ 
		return accService.searchAccountNo(acc);  
	}  
	
	@PostMapping("/account")
	private ResponseView<Account> saveAccount(@RequestBody Account acc){ 
		return accService.saveAccount(acc);
	}  
	
	@DeleteMapping("/account/{id}")
	public ResponseView<Account> deleteAccount(@PathVariable("id") int id) {
		
		return accService.deleteAccount(id);
	}
	
	@GetMapping("/account/{id}")  
	private ResponseView<Account> getAccount(@PathVariable("id") int id){  
		return accService.getAccountById(id);  
	}  
}
