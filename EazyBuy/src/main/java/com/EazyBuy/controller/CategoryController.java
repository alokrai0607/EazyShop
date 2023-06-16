package com.EazyBuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.model.Category;
import com.EazyBuy.model.Product;
import com.EazyBuy.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryService cs;
	
	@GetMapping("/category/name")
	public ResponseEntity<List<Product>> getAllProductsHandler(@RequestParam String category){
		
		List<Product> list  = cs.getAllProductsByCategory(category);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllProductsHandler(){
		
		List<Category> list  = cs.getAllCategoty();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
}
