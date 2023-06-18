package com.EazyBuy.service;

import java.util.List;

import com.EazyBuy.exception.Customer1Exception;
import com.EazyBuy.model.Address;
import com.EazyBuy.model.Customer;


public interface CustomerService {
	public Customer saveCustomer(Customer customer);

	public Customer deleteCustomer(Integer id,String key);

	public Customer updateCustomer(Customer customer, String key);

	public Customer getCustomerByID(Integer customerid);

	public List<Customer> getAllCustomer();

	public Customer saveAddress(Address address, Customer customer);
	
	public Customer removeAddress(Customer customer);
	
public Customer getCustomerDetailsByEmail(String email)throws Customer1Exception;
	
	public List<Customer> getAllCustomerDetails()throws Customer1Exception;
}
