package com.EazyBuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.model.Address;
import com.EazyBuy.model.Customer;
import com.EazyBuy.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/customerSave")
	public ResponseEntity<Customer> saveCustmerHandller( @RequestBody Customer customer) {
		Address address = customer.getAddress();
		Customer customer3 = customer;
		customer3.setAddress(address);
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		
		
		Customer customer2 = customerService.saveCustomer(customer3);
		return new ResponseEntity<>(customer2, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/customerDelete")
	public ResponseEntity<Customer> deleteCustmerHandller(@Valid @RequestBody Customer customer) {
		Customer customer2 = customerService.deleteCustomer(customer.getCustomerId());
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}

	
	@PutMapping("/customerUpdate")
	public ResponseEntity<Customer> updateCustmerHandller(@Valid @RequestBody Customer customer) {
		Customer customer2 = customerService.updateCustomer(customer);
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}
	

	@GetMapping("/customerGet/{id}")
	public ResponseEntity<Customer> getCustmerByIdHandller(@Valid @PathVariable Integer id) {
		Customer customer2 = customerService.getCustomerByID(id);
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomersHandller() {
		List<Customer> customers = customerService.getAllCustomer();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		System.out.println(auth); // this Authentication object having Principle object details
		
		 Customer customer= customerService.getCustomerDetailsByEmail(auth.getName());
		 
		 return new ResponseEntity<>(customer.getFirstName()+"Logged In Successfully", HttpStatus.ACCEPTED);	
	}


}
