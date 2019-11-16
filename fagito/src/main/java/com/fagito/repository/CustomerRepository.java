package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Customer;
import com.fagito.model.Restaurant;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,String>{
	
	@Query(value="SELECT c.customer_id FROM customer c ORDER BY c.customer_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT * FROM customer c where c.customer_id = :customer_id",nativeQuery=true)
	Customer findByCustomerId(@Param("customer_id") String customer_id);
}
