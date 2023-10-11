package com.EazyBuy.controller;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.User;
import com.EazyBuy.repository.UserRepository;
import com.EazyBuy.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private CartService cartService;
	

	
	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, CartService cartService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.cartService = cartService;
	}

	
	@PostMapping("/signup")
	public ResponseEntity<User> createUserHandler(@Valid @RequestBody User user) throws UserException{
		
		  	String email = user.getEmail();
	        String password = user.getPassword();
	        String firstName=user.getFirstName();
	        String lastName=user.getLastName();
	        
	        User isEmailExist=userRepository.findByEmail(email);

	        // Check if user with the given email already exists
	        if (isEmailExist!=null) {
	        // 
	        	
	            throw new UserException("Email Is Already Used With Another Account");
	        }

	        // Create new user
			User createdUser= new User();
			createdUser.setEmail(email);
			createdUser.setFirstName(firstName);
			createdUser.setLastName(lastName);
	        createdUser.setPassword(passwordEncoder.encode(password));
	        createdUser.setRole("ROLE_USER");
	        createdUser.setCreatedAt(LocalDateTime.now());
	        createdUser.setMobile(user.getMobile());
	        
	        
	        
	        User savedUser= userRepository.save(createdUser);
	        
	        cartService.createCart(savedUser);

	        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
	        SecurityContextHolder.getContext().setAuthentication(authentication);

			
	        return new ResponseEntity<>(savedUser,HttpStatus.OK);
		
	}
	
	@GetMapping("/signin")
    public ResponseEntity<User> signin(Authentication authentication) {
		String email = authentication.getName();
		User user = userRepository.findByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
	
	
}
