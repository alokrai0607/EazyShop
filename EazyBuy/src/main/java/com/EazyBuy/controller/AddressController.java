package com.EazyBuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.exception.CustomerException;
import com.EazyBuy.model.Address;
import com.EazyBuy.model.Customer;
import com.EazyBuy.service.AddressService;
import com.EazyBuy.service.CustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	 private  CustomerService customerService;
	
	@GetMapping("/address/{aid}")
	public ResponseEntity<Address> getAddressByIdHanller(@PathVariable Integer aid) throws CustomerException {
		Address address = addressService.getAddress(aid);
		return new ResponseEntity<>(address,HttpStatus.OK);
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAddressByIdHanller() throws CustomerException {
		List<Address> address = addressService.getAllAddress();
		return new ResponseEntity<>(address,HttpStatus.OK);
	}
	@PostMapping("/customers/{customerId}/addresses")
    public ResponseEntity<Address> createAddressForCustomer(@PathVariable Integer customerId, @RequestBody Address address) throws CustomerException {
        Customer customer = customerService.getCustomerByID(customerId);
        address.setCustomer(customer);
        Address savedAddress = addressService.createAddress(address, customerId);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

}
