package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.pojo.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	public UserDetailServiceImpl() {
		System.out.println("-----ctor-" + getClass().getName() + "---");
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
		User user = userRepo.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(),
				new ArrayList<GrantedAuthority>());
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		User user = userRepo.findById(id).get();
		return new org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(),
				new ArrayList<GrantedAuthority>());
	}
}
