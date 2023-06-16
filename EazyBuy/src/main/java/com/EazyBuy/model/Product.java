package com.EazyBuy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private String image;
	private double price;
	private String description;
	private String manufacturer;
	private Integer quantity;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="catId")
	private Category category;


	
	
}
