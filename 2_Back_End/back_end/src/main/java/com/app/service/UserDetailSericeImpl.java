package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.pojo.User;

@Service
public class UserDetailSericeImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	/*
	 * o.s.s.c.userdetails.UserDetails : represents core user information. It stores
	 * user information which is later encapsulated into Authentication object. This
	 * allows non-security related additional user information (eg : email
	 * addresses,telephone numbers ) to be stored in a convenient location.
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("in load user " + userName);
		// replaced userName by email
//		User user = userRepo.findByUserName(userName)
//				.orElseThrow(() -> new UsernameNotFoundException("User Name " + userName + " not found!!!"));
		User user = userRepo.findByEmail(userName);
		return new UserDetailmpl(user);
	}
}
