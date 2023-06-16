package com.EazyBuy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EazyBuy.exception.OrderException;
import com.EazyBuy.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
	public List<Orders> findByOrderDate(LocalDate orderDate) throws OrderException;
	

	
}
