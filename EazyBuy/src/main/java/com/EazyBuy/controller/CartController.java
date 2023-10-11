package com.EazyBuy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.CartItem;
import com.EazyBuy.model.User;
import com.EazyBuy.request.AddItemRequest;
import com.EazyBuy.service.CartService;
import com.EazyBuy.service.UserService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	private CartService cartService;
	private UserService userService;
	
	public CartController(CartService cartService,UserService userService) {
		this.cartService=cartService;
		this.userService=userService;
	}
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Cart cart=cartService.findUserCart(user.getId());
		
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		CartItem createdCartItem = cartService.addCartItem(user.getId(), req);
		
		
		return new ResponseEntity<>(createdCartItem,HttpStatus.ACCEPTED);
		
	}
	

}
