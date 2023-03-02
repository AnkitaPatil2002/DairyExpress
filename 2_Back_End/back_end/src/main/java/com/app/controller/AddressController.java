package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.app.dto.ResponseDto;
import com.app.pojo.Address;
import com.app.service.AddressService;
import com.app.service.UserService;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {

	@Autowired
	private AddressService addrService;

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllAddresses() {
		Integer UserID = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<>(
				new ResponseDto<List<Address>>("success", addrService.GetAllAddressessByUserId(UserID)), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addNewAddress(@RequestBody Address addr) throws UserNotFoundException {
		Integer UserID = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(UserID + " Hell from address adding ");
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		addr.setSelectedUser(userService.findById(UserID));
		return new ResponseEntity<>(new ResponseDto<Address>("success", addrService.AddOrEditAddress(addr)),
				HttpStatus.CREATED);
	}

	@PutMapping("/edit/{addrId}")
	public ResponseEntity<?> editAddressById(@RequestBody Address addr, @PathVariable Integer addrId)
			throws UserNotFoundException {
		Integer userId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
		addr.setSelectedUser(userService.findById(userId));
		addr.setId(addrId);
		return new ResponseEntity<>(new ResponseDto<Address>("success", addrService.AddOrEditAddress(addr)),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete/{addrId}")
	public ResponseEntity<?> deleteAddressById(@PathVariable Integer addrId) {
		return new ResponseEntity<>(new ResponseDto<String>("success", addrService.DeleteAddressById(addrId)),
				HttpStatus.OK);
	}
}
