package com.yoma.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoma.model.Customer;
import com.yoma.model.view.CustomerAccountDetail;
import com.yoma.model.view.ResponseView;
import com.yoma.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService custService;
	
	@GetMapping("/customer")
	public ResponseView<List<Customer>> getCustomerInfo(){		
		return custService.getAllCustomerList();
	}
	
	
	@GetMapping("/customer/{id}")  
	private ResponseView<Customer> getCustomer(@PathVariable("id") int id){  
		return custService.getCustomerById(id);  
	}  
	
	@PostMapping("/customer")
	private ResponseView<Customer> saveCustomer(@RequestBody Customer cust){ 
		return custService.saveCustomer(cust);
	}  
	
	@DeleteMapping("/customer/{id}")
	public ResponseView<Customer> deleteCustomer(@PathVariable("id") int id) {
		
		return custService.deleteCustomer(id);
	}
	
	@PostMapping("/customer_detail")
	private ResponseView<List<CustomerAccountDetail>> getCustomerDetail(@RequestBody Customer cust){  
		return custService.getCustomerAccount(cust);  
	}  
	
	@DeleteMapping("/customer_delete/{id}")
	public void deleteCustomerAndAcccount(@PathVariable("id") int id) {
		custService.deleteCustomerAndAcccount(id);
	}
	
}
