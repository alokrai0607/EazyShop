package com.EazyBuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.CustomerException;
import com.EazyBuy.model.Address;
import com.EazyBuy.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepo addressRepo;

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
}
