package com.EazyBuy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data

public class Admin {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
   @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    
    @Column(name = "password", nullable = false)
    private String password;
    
    

}
