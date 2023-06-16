package com.EazyBuy.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.AdminException;
import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.Admin;
import com.EazyBuy.model.CurrentUserSession;
import com.EazyBuy.model.Customer;
import com.EazyBuy.model.User;
import com.EazyBuy.repository.AdminRepository;
import com.EazyBuy.repository.CustomerRepo;
import com.EazyBuy.repository.UserSession;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private UserSession session;

	@Autowired
	private AdminRepository adminRepo;

	@Override
	public CurrentUserSession loginToAccount(User user) {
		if (user.getType().equalsIgnoreCase("customer")) {
			Customer customer = customerRepo.findByMobileNumber(user.getUserId());
			if (customer == null)
				throw new UserException("Mobile number does not exixts !");

			Optional<CurrentUserSession> optional = session.findById(customer.getCustomerId());

			if (optional.isPresent())
				throw new UserException(" Customer is already Logged In");
			if (customer.getPassword().equalsIgnoreCase(user.getPassword())) {
				String key = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession(customer.getCustomerId(), key,
						LocalDateTime.now());

				return session.save(currentUserSession);
			} else {
				throw new UserException("Please Provide valid password");
			}
		} else if (user.getType().equalsIgnoreCase("admin")) {
			Admin admin = adminRepo.findByEmail(user.getUserId());

			if (admin == null) {
				throw new AdminException("Admin with this email id does not exists");
			}

			Optional<CurrentUserSession> optional = session.findById(admin.getId());

			if (optional.isPresent()) {
				throw new AdminException("Admin is already Logged In");
			}

			if (admin.getPassword().equalsIgnoreCase(user.getPassword())) {
				String key = RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession(admin.getId(), key, LocalDateTime.now());

				return session.save(currentUserSession);

			} else {
				throw new UserException("Wrong password ");

			}
		} else {
			throw new UserException("Invalid User detailes ");
		}

	}

	@Override
	public String logOutFromAccount(String key) {
		CurrentUserSession currentUserSession = session.findByUuid(key);
		if (currentUserSession.equals(null))
			throw new UserException("User is not Logged In with this number");

		session.delete(currentUserSession);
		return "Logged Out Successfully !";
	}

}
