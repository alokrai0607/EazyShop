package com.EazyBuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EazyBuy.model.CurrentUserSession;

public interface UserSession extends JpaRepository<CurrentUserSession, Integer>{

	
	public  CurrentUserSession  findByUuid(String uuid);
	
}
