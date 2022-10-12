package com.yoma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoma.model.Customer;
import com.yoma.model.Trans;
import com.yoma.model.view.ResponseView;
import com.yoma.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService tranService;
	
	@PostMapping("/trans")
	private ResponseView<List<Trans>> getTrans(@RequestBody Trans trans){ 
		return tranService.getTrans(trans);
	}
	
	@GetMapping("/trans")
	public ResponseView<List<Trans>> getAllTrans(){		
		return tranService.getAllTrans();
	}
}
