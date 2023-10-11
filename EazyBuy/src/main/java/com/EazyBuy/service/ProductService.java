package com.EazyBuy.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.model.Product;
import com.EazyBuy.request.CreateProductRequest;

public interface ProductService {
	public Product createProduct(CreateProductRequest req) throws ProductException, SQLException;
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId,Product product)throws ProductException;
	
	public List<Product> getAllProducts();
	
	// for user and admin both
	public Product findProductById(Long id) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public List<Product> searchProduct(String query);
	
	public Page<Product> getAllProduct(String category, List<String>colors, List<String> sizes, Integer minPrice, Integer maxPrice,String sort, String stock, Integer pageNumber, Integer pageSize);
	
	Page<Product> getProductsSortedByPrice(String sortDirection, int page, int pageSize);
	Page<Product> getProductsByCategory(String categoryName, int page, int pageSize);
	Page<Product> getProductsByCategoryAndPriceRange(
            String categoryName, Integer minPrice, Integer maxPrice, int page, int pageSize);
	
	

}
