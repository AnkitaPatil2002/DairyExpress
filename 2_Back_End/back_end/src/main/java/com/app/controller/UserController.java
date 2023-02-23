package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exp.UserNotFoundException;
import com.app.dto.LoginRequest;
import com.app.dto.LoginResponse;
import com.app.dto.ResponseDto;
import com.app.pojo.User;
import com.app.service.UserService;
import com.app.util.JwtUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;

	public UserController() {
		System.out.println("-----ctor " + getClass().getName() + "----------");
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		System.out.println("in create new user" + user);
		return new ResponseEntity<>(new ResponseDto<User>("success", userService.registerOrEditUser(user)),
				HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) throws UserNotFoundException {
		System.out.println("in auth user" + request);
		try {
			// authenticate user
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			System.out.println("\n------ Authenticated userDetails:" + authenticate + " -------\n");
		} catch (Exception e) {
			throw new UserNotFoundException("Invalid UserName or password");
		}
		User user = userService.findByEmail(request.getEmail());
		return new ResponseEntity<>(new LoginResponse("success", user, jwtUtil.generateToken(user.getId())),
				HttpStatus.OK);
	}

	@PutMapping("/edit/{uid}")
	public ResponseEntity<?> editUser(@RequestBody User user, @PathVariable long uid) {
		user.setId(uid);
		return new ResponseEntity<>(new ResponseDto<User>("success", userService.registerOrEditUser(user)),
				HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<>(new ResponseDto<List<User>>("Success", userService.getUsersByRole("CUSTOMER")),
				HttpStatus.OK);
	}

	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployees() {
		return new ResponseEntity<>(new ResponseDto<List<User>>("Success", userService.getUsersByRole("EMPLOYEE")),
				HttpStatus.OK);
	}

	@GetMapping("/delivery_person")
	public ResponseEntity<?> getAllDeliveryPerson() {
		return new ResponseEntity<>(
				new ResponseDto<List<User>>("Success", userService.getUsersByRole("DELIVERY_PERSON")), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<?> deleteById(@PathVariable long uid) {
		return new ResponseEntity<>(new ResponseDto<String>("Success", userService.deleteUserById(uid)), HttpStatus.OK);
	}
}
