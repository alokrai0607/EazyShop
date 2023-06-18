package com.EazyBuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.CustomerException;
import com.EazyBuy.model.Address;
import com.EazyBuy.model.Customer;
import com.EazyBuy.repository.AddressRepo;
import com.EazyBuy.repository.CustomerRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public List<Address> getAllAddress() {
		List<Address> addresses = addressRepo.getAllAddress();
		return addresses;
	}

	@Override
	public Address getAddress(Integer aid) throws CustomerException {

		Optional<Address> address = addressRepo.findById(aid);

		if (address.isEmpty())
			throw new CustomerException("Wrong address ID");
		return address.get();
	}
	@Override
	public Address createAddress(Address address, Integer customerId) throws CustomerException {
	    Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
	    if (optionalCustomer.isEmpty()) {
	        throw new CustomerException("Invalid customer ID");
	    }
	    Customer c = optionalCustomer.get();
	    address.setCustomer(c);
	    Address savedAddress = addressRepo.save(address);

	    return savedAddress;
	}

}
