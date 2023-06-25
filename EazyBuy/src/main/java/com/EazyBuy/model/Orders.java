package com.EazyBuy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer orderId;
	private LocalDate orderDate;

	private String orderStatus;
	
	@Embedded
	@ElementCollection
	List<Product> productList= new ArrayList<>();
	
	
	@ManyToOne
	
	@JoinColumn(name = "CID")
	private Customer customer;
	
	@OneToOne
	
	@JoinColumn(name="AID")
	private Address address;

}
