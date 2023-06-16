package com.EazyBuy.Service;

import java.util.List;

import com.masai.Exception.CartException;
import com.masai.model.Cart;
import com.masai.model.Product;


public interface CartService {
	
	
	public Cart addProductToCart(Integer cartId, Integer productId)throws CartException;
	
	public Cart removeProductFromCart(Integer cartId, Integer productId)throws CartException;
	
	public Cart updateProductQantity(Integer cartId, Integer productId, Integer quantity)throws CartException;
	
	public Cart removeAllProducts(Integer cartId)throws CartException ;
	
	public List<Product>  viewAllProducts(Integer cartId)throws CartException ;
	
	

}
