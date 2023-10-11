package com.EazyBuy.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.EazyBuy.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	@Query("select a from Address a")
	public List<Address> getAllAddress() ;
}
