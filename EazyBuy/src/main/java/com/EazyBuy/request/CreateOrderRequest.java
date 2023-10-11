package com.EazyBuy.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

	private String firstName;

	private String lastName;
	

    private String streetAddress;


    private String city;


    private String state;

    private String zipCode;
    
    private String mobile;
    private String paymentMethod;
	private String status;
	private String paymentId;

	private String cardholderName;

	private String cardNumber;

}
