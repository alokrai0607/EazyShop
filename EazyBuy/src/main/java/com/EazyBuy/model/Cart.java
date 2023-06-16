package com.EazyBuy.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	
	
	@OneToMany
	private List<Product> product;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;

	
	
	
	
}
