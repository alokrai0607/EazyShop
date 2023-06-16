package com.EazyBuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EazyBuy.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {


}
