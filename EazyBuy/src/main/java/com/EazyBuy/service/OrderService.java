package com.EazyBuy.service;

import java.time.LocalDate;
import java.util.List;

import com.EazyBuy.exception.CartException;
import com.EazyBuy.exception.OrderException;
import com.EazyBuy.model.Orders;

public interface OrderService {
	
	public Orders addOrder(Integer customerId) throws OrderException,CartException;
	public Orders updateOrder(Orders order) throws OrderException;
	public String removeOrder(Integer orderId) throws OrderException;
	public Orders viewOrderById(Integer orderId) throws OrderException;
	public List<Orders> AllOrder() throws OrderException;
	public List<Orders> AllOrderByDate(LocalDate date) throws OrderException;
	public List<Orders> AllOrderByLocation(String location) throws OrderException;
	
}
