package com.EazyBuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.model.Category;
import com.EazyBuy.model.Product;
import com.EazyBuy.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService ps;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {

		return new ResponseEntity<>(ps.viewAllProduct(), HttpStatus.OK);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {

		return new ResponseEntity<>(ps.getProductById(id), HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestParam("sessionKey") String key) {

		return new ResponseEntity<>(ps.addProduct(product, key), HttpStatus.OK);
	}

	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @RequestParam("sessionKey") String key) {

		return new ResponseEntity<>(ps.updateProduct(product, key), HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("id") Integer id,
			@RequestParam("sessionKey") String key) {

		return new ResponseEntity<>(ps.deleteProductById(id, key), HttpStatus.OK);
	}

	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@RequestBody Category category,
			@RequestParam("sessionKey") String key) {

		return new ResponseEntity<>(ps.addCategory(category, key), HttpStatus.OK);
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") Integer id,
			@RequestParam("sessionKey") String key) {

		return new ResponseEntity<>(ps.deleteCategory(id, key), HttpStatus.OK);
	}
}
