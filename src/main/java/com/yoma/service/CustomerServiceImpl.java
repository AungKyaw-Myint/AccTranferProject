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
import com.yoma.model.Customer;
import com.yoma.model.view.CustomerAccountDetail;
import com.yoma.model.view.ResponseView;
import com.yoma.repo.AccountRepo;
import com.yoma.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepo custRepo;
	
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	ErrorHandling errorHandle;

	@Override
	public ResponseView<List<Customer>> getAllCustomerList() {
		ResponseView<List<Customer>> resp=new ResponseView<>();
		List<Customer> accList=new ArrayList<Customer>();
		try {
			accList=custRepo.findAll();
			resp.setStatusCode("200");
			resp.setMessage("Account List");
			resp.setModel(accList);
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		return resp;
	}

	@Override
	public ResponseView<Customer> getCustomerById(int id) {
		ResponseView<Customer> resp=new ResponseView<>();
		Customer cust=new Customer();
		try {
			cust=custRepo.findByCustomerId(id);
			
			if(cust == null) {
				throw new NoDataFoundException();
			}
			System.out.println(cust.getDel_flag());
			if(cust.getDel_flag()!=0) {
				throw new AlreadyDeletedException();
			}
			
			resp.setModel(cust);
			resp.setStatusCode("200");
			resp.setMessage("Success");
		} catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		return resp;
	}

	@Override
	@Transactional(rollbackFor =Exception.class)
	public ResponseView<Customer> saveCustomer(Customer cust) {
		
		ResponseView<Customer> resp=new ResponseView<>();
		
		try {
			//update Data
			if(cust.getId()!=0) {
				custRepo.save(cust);
				resp.setStatusCode("200");
				resp.setMessage("Customer Data Update Successfully!");
				resp.setModel(custRepo.findByCustomerId(cust.getId()));
			}else {
				custRepo.save(cust);
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
	public ResponseView<Customer> deleteCustomer(int id) {
		ResponseView<Customer> resp=new ResponseView<>();
		Customer cust=new Customer();
		try {
			cust=custRepo.findByCustomerId(id);
			
			if(cust == null) {
				throw new NoDataFoundException();
			}
			if(cust.getDel_flag()!=0) {
				throw new AlreadyDeletedException();
			}
			
			custRepo.deleteCustomer(id);
			resp.setStatusCode("200");
			resp.setMessage("Successfully Deleted!");
		} catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		return resp;
	}

	@Override
	@Transactional(rollbackFor =Exception.class)
	public ResponseView<List<CustomerAccountDetail>> getCustomerAccount(Customer cust) {
		ResponseView<List<CustomerAccountDetail>> resp=new ResponseView<>();
		List<CustomerAccountDetail> accDetail=new ArrayList<CustomerAccountDetail>();
		List<Customer> custList=new ArrayList<>();
		CustomerAccountDetail custAccDetail=new CustomerAccountDetail();
		try {
		
			custList=custRepo.findByCustomerName(cust.getCust_name());
			
			for (Customer temp : custList) {
				custAccDetail=new CustomerAccountDetail();
				custAccDetail.setId(temp.getId());
				custAccDetail.setCust_name(temp.getCust_name());
				custAccDetail.setEmail(temp.getEmail());
				custAccDetail.setPh_no(temp.getPh_no());
				custAccDetail.setDel_flag(temp.getDel_flag());
				custAccDetail.setAccList(accRepo.getAccountByCustomer(temp.getId()));
				
				accDetail.add(custAccDetail);
	        }
			
			resp.setModel(accDetail);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return resp;
	}

	@Override
	public void deleteCustomerAndAcccount(int id) {
		custRepo.deleteById(id);
		
	}

}
