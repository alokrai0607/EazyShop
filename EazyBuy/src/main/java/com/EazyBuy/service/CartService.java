package com.EazyBuy.service;

import java.util.List;

import com.EazyBuy.exception.CartException;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.Product;


public interface CartService {
	
	
	public Cart addProductToCart(Integer cartId, Integer productId)throws CartException;
	
	public Cart removeProductFromCart(Integer cartId, Integer productId)throws CartException;
	
	public Cart updateProductQantity(Integer cartId, Integer productId, Integer quantity)throws CartException;
	
	public Cart removeAllProducts(Integer cartId)throws CartException ;
	
	public List<Product>  viewAllProducts(Integer cartId)throws CartException ;
	
	

}
