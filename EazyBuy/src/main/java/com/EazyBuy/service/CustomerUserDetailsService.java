package com.EazyBuy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EazyBuy.model.Customer;
import com.EazyBuy.repository.CustomerRepo;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepo customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("reached here");
		Optional<Customer> opt= customerRepository.findByEmail(username);
		System.out.println("reached after finding");

		if(opt.isPresent()) {
			System.out.println("reacahed also here");
			Customer customer= opt.get();
			
			List<GrantedAuthority> authorities= new ArrayList<>();	
			
			SimpleGrantedAuthority sga= new SimpleGrantedAuthority(customer.getRole());
			authorities.add(sga);
			
			return new User(customer.getEmail(), customer.getPassword(), authorities);
		
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
	}

}
