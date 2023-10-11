package com.EazyBuy.controller;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.model.Product;
import com.EazyBuy.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllListOfProduct(){
    	 List<Product> list = productService.getAllProducts();
    	 return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/sorted")
    public Page<Product> getProductsSortedByDiscountedPrice(
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return productService.getProductsSortedByPrice(sort, page, pageSize);
    }
    @GetMapping("/by-category")
    public Page<Product> getProductsByCategory(
            @RequestParam String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return productService.getProductsByCategory(categoryName, page, pageSize);
    }
    @GetMapping("/by-category-and-price")
    public Page<Product> getProductsByCategoryAndPriceRange(
            @RequestParam String categoryName,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return productService.getProductsByCategoryAndPriceRange(
                categoryName, minPrice, maxPrice, page, pageSize);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) List<String> colors,
            @RequestParam(required = false) List<String> sizes,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minDiscount,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String stock,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Page<Product> page =  productService.getAllProduct(
                category,
                colors,
                sizes,
                minPrice,
                maxPrice,
                sort,
                stock,
                pageNumber,
                pageSize
        );
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductException {
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }
    @GetMapping("/products")
	public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
			@RequestParam List<String>color,@RequestParam List<String> size,@RequestParam Integer minPrice,
			@RequestParam Integer maxPrice,  @RequestParam String sort, 
			@RequestParam String stock, @RequestParam Integer pageNumber,@RequestParam Integer pageSize){

		
		Page<Product> res= productService.getAllProduct(category, color, size, minPrice, maxPrice, sort,stock,pageNumber,pageSize);
		
		System.out.println("complete products");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
	

	
	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException{
		
		Product product=productService.findProductById(productId);
		
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}

	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q){
		
		List<Product> products=productService.searchProduct(q);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
}

