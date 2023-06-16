package com.EazyBuy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CustomerException;
import com.masai.Repository.AddressRepo;
import com.masai.model.Address;

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
