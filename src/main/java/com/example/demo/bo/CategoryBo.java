package com.example.demo.bo;

import java.util.List;

import com.example.demo.payload.CategoryDto;

public interface CategoryBo {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto);
	CategoryDto findById(int categoryId);
	List<CategoryDto> findAll();
	String deleteCategory(int categoryId);
}
