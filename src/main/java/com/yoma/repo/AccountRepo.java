package com.yoma.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yoma.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{
	
	@Query(value="select * from account where acc_id=?1", nativeQuery = true)
	Account findByAccountId(int id);
	
	@Modifying
	@Query(value="update account set del_flag='1' where acc_id=?1",nativeQuery = true)
	void deleteAccount(int id);
	
	@Query(value="select * from account where acc_no=?1", nativeQuery = true)
	Account searchAccountNo(String accNo);
	
	@Query(value="select * from account where customer_id=?1", nativeQuery = true)
	List<Account> getAccountByCustomer(int id);
}
