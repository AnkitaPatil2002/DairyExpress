package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojo.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
	
	@Query("Select a from Address a join fetch a.selectedUser where a.selectedUser.id=:id")
	List<Address>GetAllAddressesByUserId(@Param("id")Long userId);
}
