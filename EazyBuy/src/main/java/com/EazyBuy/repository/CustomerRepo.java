package com.EazyBuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.EazyBuy.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	public Customer findByMobileNumber(String mobileNumber) ;
	
	@Query("select c from Customer c")
	public List<Customer> getAllCustmer();
	
	public Optional<Customer> findByEmail(String email);

}
