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

import com.example.demo.bo.RecipeBo;
import com.example.demo.payload.RecipeDto;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	RecipeBo recipeBo;

	@PostMapping("/create")
	RecipeDto createRecipe(@RequestBody RecipeDto recipeDto) {
		return recipeBo.createRecipe(recipeDto);
	}

	@PutMapping("/update")
	RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto) {
		return recipeBo.updateRecipe(recipeDto);
	}

	@GetMapping("/findById/{recipeId}")
	RecipeDto findById(@PathVariable("recipeId") int recipeId) {
		return recipeBo.findById(recipeId);
	}

	@GetMapping("/findAll")
	List<RecipeDto> findAll() {
		return recipeBo.findAll();
	}

	@DeleteMapping("/delete/{recipeId}")
	String deleteRecipe(@PathVariable("recipeId") int recipeId) {
		return recipeBo.deleteRecipe(recipeId);
	}
}
