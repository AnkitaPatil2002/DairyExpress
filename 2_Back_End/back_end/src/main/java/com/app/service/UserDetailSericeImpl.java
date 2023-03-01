package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("in load user " + userName);
		User user = userRepo.findByEmail(userName);
		return new UserDetailmpl(user);
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {

		System.out.println("\n------------ IN UserDetailsServiceImpl.loadUserById ---------------\n");

		System.out.println("Id: " + id);

		User user = userRepo.findById(id).get();

		System.out.println("User from loadUserById: " + user);
		return new org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(),
				new ArrayList<GrantedAuthority>());
	}
}
