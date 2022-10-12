package com.yoma.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yoma.model.Customer;
import com.yoma.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{
	
	@Query(value="select * from payment where account_no=?1", nativeQuery = true)
	List<Payment> findPaymentAccount(String id);

}
