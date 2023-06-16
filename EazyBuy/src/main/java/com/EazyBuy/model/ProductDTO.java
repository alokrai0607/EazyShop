package com.EazyBuy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

	private Integer productId;
	private String productName;
	private double price;
	private String description;
	private String manufacturer;
	private Integer quantity;
	
	public ProductDTO(Integer productId, String productName, double price, String description, String manufacturer,
			Integer quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
	}
	 
}
