package com.EazyBuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EazyBuy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
}
