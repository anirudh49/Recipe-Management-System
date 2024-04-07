package com.example.demo.bo;

import java.util.List;

import com.example.demo.payload.RecipeDto;

public interface RecipeBo {

	RecipeDto createRecipe(RecipeDto recipeDto);
	RecipeDto updateRecipe(RecipeDto recipeDto);
	RecipeDto findById(int recipeId);
	List<RecipeDto> findAll();
	String deleteRecipe(int recipeId);
}
