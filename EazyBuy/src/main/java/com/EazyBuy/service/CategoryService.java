package com.EazyBuy.service;

import java.util.List;

import com.EazyBuy.model.Category;
import com.EazyBuy.model.Product;

public interface CategoryService {

	

	public List<Product> getAllProductsByCategory(String catName);

	public List<Category> getAllCategoty();
}
