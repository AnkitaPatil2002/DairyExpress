package com.app.service;

import java.util.List;

import com.app.pojo.Address;

public interface AddressService {

	List<Address> GetAllAddressessByUserId(Integer userId);

	Address AddOrEditAddress(Address addr);

	String DeleteAddressById(Integer userId);
}
