package com.app.service;

import java.util.List;

import com.app.custom_exp.UserNotFoundException;
import com.app.dto.LoginRequest;
import com.app.pojo.User;

public interface UserService {

	User registerOrEditUser(User user);

	User authnticateUser(LoginRequest request) throws UserNotFoundException;

	User findByEmail(String email);

	User findById(Integer userId) throws UserNotFoundException;

	List<User> getUsersByRole(String role);

	String deleteUserById(Integer Id);
}
