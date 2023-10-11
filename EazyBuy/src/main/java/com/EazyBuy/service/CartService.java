package com.EazyBuy.service;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.CartItem;
import com.EazyBuy.model.User;
import com.EazyBuy.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
