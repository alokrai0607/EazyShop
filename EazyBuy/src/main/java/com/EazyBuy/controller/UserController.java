package com.EazyBuy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.User;
import com.EazyBuy.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException{

		System.out.println("/api/users/profile");
		User user=userService.findUserProfileByJwt(jwt);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}

}
