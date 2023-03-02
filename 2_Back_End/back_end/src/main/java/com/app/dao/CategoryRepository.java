package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojo.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByName(String category);
	
}
