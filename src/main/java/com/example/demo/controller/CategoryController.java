package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.CategoryBo;
import com.example.demo.payload.CategoryDto;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryBo categoryBo;
	
	@PostMapping("/create")
	CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
		return categoryBo.createCategory(categoryDto);
	}
	
	@PutMapping("/update")
	CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
		return categoryBo.updateCategory(categoryDto);
	}
	
	@GetMapping("/findById/{categoryId}")
	CategoryDto findById(@PathVariable("categoryId") int categoryId) {
		return categoryBo.findById(categoryId);
	}
	
	@GetMapping("/findAll")
	List<CategoryDto> findAll(){
		return categoryBo.findAll();
	}
	
	@DeleteMapping("/delete/{categoryId}")
	String deleteCategory(@PathVariable("categoryId") int categoryId) {
		return categoryBo.deleteCategory(categoryId);
	}
}
