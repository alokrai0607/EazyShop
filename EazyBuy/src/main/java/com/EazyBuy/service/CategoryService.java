package com.EazyBuy.Service;

import java.util.List;

import com.masai.model.Category;
import com.masai.model.CategoryEnum;
import com.masai.model.Product;

public interface CategoryService {

	

	public List<Product> getAllProductsByCategory(String catName);

	public List<Category> getAllCategoty();
}
