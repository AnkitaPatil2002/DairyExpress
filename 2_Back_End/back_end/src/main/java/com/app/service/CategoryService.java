package com.app.service;

import java.util.List;

import com.app.pojo.Category;

public interface CategoryService {

	Category addOrEditCategory(Category cat);

	List<Category> getAllCategories();

	String deleteCategoryById(Integer id);

	Category findByName(String categoryName);
}
