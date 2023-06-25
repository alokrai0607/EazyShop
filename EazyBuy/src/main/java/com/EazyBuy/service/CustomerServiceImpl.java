package com.EazyBuy.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.AlreadyExistedException;
import com.EazyBuy.exception.Customer1Exception;
import com.EazyBuy.exception.InputInvalidException;
import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.Address;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.CurrentUserSession;
import com.EazyBuy.model.Customer;
import com.EazyBuy.repository.AddressRepo;
import com.EazyBuy.repository.CartRepository;
import com.EazyBuy.repository.CustomerRepo;
import com.EazyBuy.repository.UserSession;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserSession userRepo;
	
	@Autowired
	private AddressRepo addressRepo;

	@Override
	@Transactional
	public Customer saveCustomer(Customer customer) {


		Optional<Customer> customer2 = customerRepo.findById(customer.getCustomerId());
		if (customer2.isPresent())
			throw new AlreadyExistedException("Customer already exists ");

		return customerRepo.save(customer);

	}

	@Override
	public Customer deleteCustomer(Integer id) {
		
		
		Optional<Customer> customer2 = customerRepo.findById(id);

		if (customer2.isEmpty())
			throw new InputInvalidException("Wrong Customer Id");

		Customer customer3 = customerRepo.save(customer2.get());
		return customer3;
	}

	@Override
	public Customer updateCustomer(Customer customer) {

		
		Optional<Customer> customer2 = customerRepo.findById(customer.getCustomerId());

		if (customer2.isEmpty())
			throw new InputInvalidException("Wrong Customer Id");
		
			
		 
		return customerRepo.save(customer);
	}

	@Override
	public Customer getCustomerByID(Integer customerid) {
		Optional<Customer> customer2 = customerRepo.findById(customerid);
		if (customer2.isEmpty())
			throw new InputInvalidException("Wrong Customer Id");
		return customer2.get();
	}

	

	@Override
	public Address saveAddress(Address address) {


		return addressRepo.save(address);

		
	}
	@Override
	public Address removeAddress(Address address) throws SQLException {
		

          addressRepo.deleteById(address.getAddressId());
          
          return address;

		

		
	}
	
	

	

}
