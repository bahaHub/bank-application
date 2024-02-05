package com.baha.bankapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.baha.bankapp.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	@Query(value = """
		      select *
		      from taccount a
		      where b.acc_no = :accNo
		      """, nativeQuery = true)
		  Optional<Account> findByAccNo(String accNo);
	
	@Query(value = """
		      select *
		      from taccount a
		      where a.cust_no = :custNo
		      """, nativeQuery = true)
		List<Account> findByCustNo(String custNo);
}