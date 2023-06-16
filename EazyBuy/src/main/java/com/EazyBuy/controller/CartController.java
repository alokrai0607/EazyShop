package com.EazyBuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.exception.CartException;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.Product;
import com.EazyBuy.service.CartService;

@RestController
@CrossOrigin(origins = "*")
public class CartController {
	
	@Autowired
	public CartService cartService;
	
//  ADD PRODUCTS TO THE CART ==========================>
	
	
	@PostMapping("carts/addProduct")
	public ResponseEntity<Cart> addProductToCart(@RequestParam("cartId") String cartId, 
			                                     @RequestParam("productId") String productId
			                                     ) throws CartException {
			  Integer cid = Integer.parseInt(cartId) ;
			  Integer pid = Integer.parseInt(productId) ;
	     Cart cart=cartService.addProductToCart(cid, pid);
	     
	     
	     ResponseEntity<Cart> re=new ResponseEntity<>(cart , HttpStatus.CREATED);
	     
	     return re;   
		
	}
	
//  DELETE PRODUCTS BY FROM CART ==========================>
	
	
	
	@DeleteMapping("carts/deletProduct")
	public ResponseEntity<Cart> removeProductFromCart(@RequestParam("cartId") Integer cartId, 
			                                     @RequestParam("productId") Integer productId)throws CartException {
			                                                                                   
		
	     Cart cart=cartService.removeProductFromCart(cartId, productId);
	     
	     
	     ResponseEntity<Cart> re=new ResponseEntity<>(cart , HttpStatus.CREATED);
	     
	     return re;   
		
	}
		
//  UPDATE PRODUCTS BY QUANTITY ==========================>
	

	@PatchMapping("carts/updateProduct")
	public ResponseEntity<Cart> updateProductQuantity(@RequestParam("cartId") Integer cartId, 
			                                     @RequestParam("productId") Integer productId,
			                                     @RequestParam("quantity")Integer quantity) throws CartException {
		
	     Cart cart=cartService.updateProductQantity(cartId, productId, quantity);
	     
	     
	     ResponseEntity<Cart> re=new ResponseEntity<>(cart , HttpStatus.CREATED);
	     
	     return re;   
		
	}
	
	//    REMOVE ALL PRODUCTS ==========================>
	
	
	@GetMapping("carts/emptyCart")
	public ResponseEntity<Cart>  removeAllProducts(@RequestParam("cartId") Integer cartId) throws CartException {
		
		Cart cart= cartService.removeAllProducts(cartId);
		
		 ResponseEntity<Cart> re=new ResponseEntity<>(cart , HttpStatus.CREATED);
		 
		 return re;
		
	}
	
	//    VIEW ALL PRODUCTS =============================>
	
	
	
	@GetMapping("carts/viewProducts")
	public ResponseEntity<List<Product>>  viewAllProducts(@RequestParam("cartId") Integer cartId) throws CartException {
		
		List <Product> productList = cartService.viewAllProducts(cartId);
		
		 ResponseEntity<List<Product>> re=new ResponseEntity<>(productList , HttpStatus.CREATED);
		 
		 return re;
		
	}
	
	
	

}
