package com.EazyBuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.model.CurrentUserSession;
import com.EazyBuy.model.User;
import com.EazyBuy.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> logInCustomer(@RequestBody User user) {

		CurrentUserSession result = service.loginToAccount(user);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/logout")
	public ResponseEntity<String> logOutCustomer(@RequestParam(required = false) String key) {

		String result = service.logOutFromAccount(key);

		return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);

	}

}
