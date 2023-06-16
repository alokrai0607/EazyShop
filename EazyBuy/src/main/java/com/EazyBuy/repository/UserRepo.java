package com.EazyBuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EazyBuy.model.User;

public interface UserRepo extends JpaRepository<User, String>{

	

}
