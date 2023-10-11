package com.EazyBuy.service;

import com.EazyBuy.exception.CartItemException;
import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.CartItem;
import com.EazyBuy.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size, Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
