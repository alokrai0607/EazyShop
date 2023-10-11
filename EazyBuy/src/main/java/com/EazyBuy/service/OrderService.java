package com.EazyBuy.service;

import java.util.List;

import com.EazyBuy.exception.OrderException;
import com.EazyBuy.model.Order;
import com.EazyBuy.model.User;
import com.EazyBuy.request.CreateOrderRequest;

public interface OrderService {
		
public Order createOrder(User user, CreateOrderRequest orderRequest);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placedOrder(Long orderId) throws OrderException;
	
	public Order confirmedOrder(Long orderId)throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order cancledOrder(Long orderId) throws OrderException;
	
	public List<Order>getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;
	
}
