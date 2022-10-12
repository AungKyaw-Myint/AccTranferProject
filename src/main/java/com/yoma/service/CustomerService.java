package com.yoma.service;

import java.util.List;

import com.yoma.model.Customer;
import com.yoma.model.view.CustomerAccountDetail;
import com.yoma.model.view.ResponseView;

public interface CustomerService {
	
	ResponseView<List<Customer>> getAllCustomerList();
	ResponseView<Customer> getCustomerById(int id);
	ResponseView<Customer> saveCustomer(Customer cust);
	ResponseView<Customer> deleteCustomer(int id);
	ResponseView<List<CustomerAccountDetail>> getCustomerAccount(Customer cust);
	void deleteCustomerAndAcccount(int id);
}
