package com.EazyBuy.service;

import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
