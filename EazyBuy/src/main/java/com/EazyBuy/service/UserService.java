package com.EazyBuy.service;

import com.EazyBuy.model.CurrentUserSession;
import com.EazyBuy.model.User;

public interface UserService {
	public CurrentUserSession loginToAccount(User user) ;
	public String logOutFromAccount(String key);

}
