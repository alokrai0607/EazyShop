package com.EazyBuy.service;

import java.util.List;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.model.Category;
import com.EazyBuy.model.Product;

public interface ProductService {

	public List<Product> viewAllProduct()throws ProductException;
	
	public Product addProduct(Product product)throws ProductException;
	
	public Product updateProduct(Product product)throws ProductException;
	
	public Product getProductById(Integer id)throws ProductException;
	
	public Product deleteProductById(Integer id) throws ProductException;

//	public List<Product> getAllProductByCategoryName(String name)throws ProductException;
	
	public Category deleteCategory(Integer catId ) throws ProductException;
	
	public Category addCategory(Category category) throws ProductException;
	Product findProductByProductName(String productName) throws ProductException;
}
