package com.baha.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.baha.bankapp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	@Query(value = """
		      select *
		      from tcustomer a
		      where a.cust_no = :custNo
		      """, nativeQuery = true)
		List<Customer> findByCustNo(String custNo);
}
