package com.EazyBuy.service;

import java.util.List;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.model.Category;
import com.EazyBuy.model.Product;

public interface ProductService {

	public List<Product> viewAllProduct()throws ProductException;
	
	public Product addProduct(Product product,String key)throws ProductException;
	
	public Product updateProduct(Product product,String key)throws ProductException;
	
	public Product getProductById(Integer id)throws ProductException;
	
	public Product deleteProductById(Integer id,String Key) throws ProductException;

//	public List<Product> getAllProductByCategoryName(String name)throws ProductException;
	
	public Category deleteCategory(Integer catId , String Key) throws ProductException;
	
	public Category addCategory(Category category , String Key) throws ProductException;
}
