package com.EazyBuy.service;

import java.util.List;

import com.EazyBuy.exception.CustomerException;
import com.EazyBuy.model.Address;


/**
 * The AddressService interface provides methods for managing addresses.
 */
public interface AddressService {
	
	  
    /**
     * Retrieves a list of all addresses.
     *
     * @return A list of Address objects.
     */
	public List<Address> getAllAddress();
	
	/**
     * Retrieves an address by its ID.
     *
     * @param aid The ID of the address to retrieve.
     * @return The Address object corresponding to the ID.
     * @throws CustomerException if the address with the specified ID is not found.
     */
	public Address getAddress(Integer aid) throws CustomerException;

	Address createAddress(Address address, Integer id) throws CustomerException;
}
