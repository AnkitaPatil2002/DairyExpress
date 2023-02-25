package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.custom_exp.CategoryNotFoundException;
import com.app.dao.CategoryRepository;
import com.app.pojo.Category;

public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categorRepo;
	@Autowired
	private ProductService prodService;

	@Override
	public Category addOrEditCategory(Category cat) {
		return categorRepo.save(cat);
	}

	@Override
	public List<Category> getAllCategories() {
		return categorRepo.findAll();
	}

	@Override
	public String deleteCategoryById(Long id) {
		Category categoryToDelete = categorRepo.findById(id).get();
		String catName = categoryToDelete.getName();
		categoryToDelete.getProducts().forEach(product -> {
			prodService.deleteProduct(product.getId());
		});
		return catName + "Category Deleted Successfully";
	}

	@Override
	public Category findByName(String categoryName) {
		return categorRepo.findByName(categoryName)
				.orElseThrow(() -> new CategoryNotFoundException("No such Category available"));
	}
}
