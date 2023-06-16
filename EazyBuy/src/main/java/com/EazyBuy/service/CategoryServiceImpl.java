package com.EazyBuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.model.Category;
import com.EazyBuy.model.Product;
import com.EazyBuy.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository catrepo;

	@Override
	public List<Product> getAllProductsByCategory(String catName) {
		
		Integer n =null;
		List<Category> list = catrepo.findAll();
		for(Category ls: list) {
			if(ls.getCatName().toString().equals(catName)) {
				n = ls.getCatId();
			}
		}
		Optional<Category> cat = catrepo.findById(n);
		 
 		return cat.get().getProduct();
	}
	
	
	
	@Override
	public List<Category> getAllCategoty(){
		return catrepo.findAll();
	}
	

	
	

}
