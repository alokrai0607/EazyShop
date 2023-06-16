package com.EazyBuy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	@Id
	@NotNull
	@NotBlank
	
	private String userId;
	@NotNull
	@NotBlank
	private String password;
	@NotNull
	@NotBlank
	private String type;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

}