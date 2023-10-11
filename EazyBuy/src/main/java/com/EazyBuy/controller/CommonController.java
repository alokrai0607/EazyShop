package com.EazyBuy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.model.Product;
import com.EazyBuy.service.ProductService;

@RestController
public class CommonController {

	private ProductService productService;

	public CommonController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProduct() {

		List<Product> products = productService.getAllProducts();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
