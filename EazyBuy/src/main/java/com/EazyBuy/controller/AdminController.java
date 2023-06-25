package com.EazyBuy.controller;

import java.time.LocalDate;
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

import com.EazyBuy.exception.OrderException;
import com.EazyBuy.model.Category;
import com.EazyBuy.model.Orders;
import com.EazyBuy.model.Product;
import com.EazyBuy.service.AdminService;
import com.EazyBuy.service.CategoryService;
import com.EazyBuy.service.OrderService;
import com.EazyBuy.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
	
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private ProductService proService;
    
    @Autowired
    private CategoryService catService;
    
	@Autowired
	private OrderService orService;
	
	
    
    @GetMapping("admin/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		return new ResponseEntity<>(proService.viewAllProduct(),HttpStatus.OK);
	}
    
    
    @GetMapping("admin/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id){
		
		return new ResponseEntity<>(proService.getProductById(id),HttpStatus.OK);
	}

    
    @PostMapping("admin/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		
		return new ResponseEntity<>(proService.addProduct(product),HttpStatus.OK);
	}
    
    
    
    @PutMapping("admin/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		
		return new ResponseEntity<>(proService.updateProduct(product),HttpStatus.OK);
	}
    
    @DeleteMapping("admin/products/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("id") Integer id){
		
		return new ResponseEntity<>(proService.deleteProductById(id ),HttpStatus.OK);
	}
    
    @GetMapping("admin/category")
	public ResponseEntity<List<Category>> getAllProductsHandler(){
		
		List<Category> list  = catService.getAllCategoty();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
    
    
    @GetMapping("admin/category/name")
	public ResponseEntity<List<Product>> getAllProductsHandler(@RequestParam String category){
		
		List<Product> list  = catService.getAllProductsByCategory(category);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
    
    @PostMapping("admin/category")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		
		return new ResponseEntity<>(proService.addCategory(category ),HttpStatus.OK );
	}
	
	@DeleteMapping("admin/category/{id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") Integer id){
		
		return new ResponseEntity<>(proService.deleteCategory(id),HttpStatus.OK);
	}
	
	@GetMapping("ordersDate/{date}")
	public ResponseEntity<List<Orders>> gelAllOrderByDateController(@PathVariable("date") LocalDate date)
			throws OrderException {

		List<Orders> allOrByDate = orService.AllOrderByDate(date);

		return new ResponseEntity<List<Orders>>(allOrByDate, HttpStatus.OK);

	}

	@GetMapping("ordersCity/{city}")
	public ResponseEntity<List<Orders>> gelAllOrderByCityController(@PathVariable("city") String city)
			throws OrderException {

		List<Orders> allOrByCitye = orService.AllOrderByLocation(city);

		return new ResponseEntity<List<Orders>>(allOrByCitye, HttpStatus.OK);

	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> allOrderController() throws OrderException {

		List<Orders> allOr = orService.AllOrder();

		return new ResponseEntity<List<Orders>>(allOr, HttpStatus.OK);
	}
	
	
}
