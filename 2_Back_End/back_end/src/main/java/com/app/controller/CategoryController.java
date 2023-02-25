package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseDto;
import com.app.pojo.Category;
import com.app.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add")
	public ResponseEntity<?> addNewCategory(@RequestBody Category category) {
		return new ResponseEntity<>(new ResponseDto<Category>("Success", categoryService.addOrEditCategory(category)),
				HttpStatus.CREATED);
	}
}
