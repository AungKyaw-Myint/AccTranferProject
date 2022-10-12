package com.yoma.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yoma.model.Trans;

@Repository
public interface TransactionRepo extends JpaRepository<Trans, Integer>{

	@Query(value="select * from transaction where from_acc=?1 or to_acc=?2 or tran_date=?3", nativeQuery = true)
	List<Trans> getTrans(String from_acc,String to_acc,String tran_date);

}
