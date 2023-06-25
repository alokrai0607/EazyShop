package com.EazyBuy.service;

import java.sql.SQLException;
import java.util.List;

import com.EazyBuy.exception.Customer1Exception;
import com.EazyBuy.model.Address;
import com.EazyBuy.model.Customer;


public interface CustomerService {
	public Customer saveCustomer(Customer customer);

	public Customer deleteCustomer(Integer id);

	public Customer updateCustomer(Customer customer);

	public Customer getCustomerByID(Integer customerid);

	public Address saveAddress(Address address);
	
	public Address removeAddress(Address address) throws SQLException;
}
