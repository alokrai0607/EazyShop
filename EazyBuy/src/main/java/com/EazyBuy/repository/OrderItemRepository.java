package com.EazyBuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EazyBuy.model.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
