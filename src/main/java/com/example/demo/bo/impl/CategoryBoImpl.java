package com.example.demo.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bo.CategoryBo;
import com.example.demo.entity.Category;
import com.example.demo.payload.CategoryDto;
import com.example.demo.repository.CategoryRepo;
@Service
public class CategoryBoImpl implements CategoryBo {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = categoryRepo.save(dtoToCategory(categoryDto));
		return categoryToDto(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		Category category = categoryRepo.findById(dtoToCategory(categoryDto).getId()).get();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		Category updatedCategory = categoryRepo.save(category);
		return categoryToDto(updatedCategory);
	}

	@Override
	public CategoryDto findById(int categoryId) {
		Category category = categoryRepo.findById(categoryId).get();
		return categoryToDto(category);
	}

	@Override
	public List<CategoryDto> findAll() {
		List<Category> categories = new ArrayList<>();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		categories = categoryRepo.findAll();
		for(int i=0;i<categories.size();i++) {
			categoryDtos.add(categoryToDto(categories.get(i)));
		}
		return categoryDtos;
	}

	@Override
	public String deleteCategory(int categoryId) {
		categoryRepo.deleteById(categoryId);
		return "Selected category has been deleted.";
	}
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	private CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
