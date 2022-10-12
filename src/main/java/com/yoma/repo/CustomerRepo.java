package com.yoma.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yoma.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	/*
	@Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
	  User findByEmailAddress(String emailAddress);
	
	@Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
	  User findByLastnameOrFirstname(@Param("lastname") String lastname,
	                                 @Param("firstname") String firstname);
	                                 
	                                 */
	@Query(value="select * from customer where customer_id=?1", nativeQuery = true)
	Customer findByCustomerId(int id);
	
	@Modifying
	@Query(value="update customer set del_flag='1' where customer_id=?1",nativeQuery = true)
	void deleteCustomer(int id);
	
	@Query(value="select * from customer where name=?1", nativeQuery = true)
	List<Customer> findByCustomerName(String name);
}
