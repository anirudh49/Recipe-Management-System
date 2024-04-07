package com.example.demo.bo;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.payload.RecipeDto;
import com.example.demo.repository.RecipeRepo;

@Service
public class RecipeBoImpl implements RecipeBo {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	RecipeRepo recipeRepo;
	
	@Override
	public RecipeDto createRecipe(RecipeDto recipeDto) {
		Recipe recipe =  recipeRepo.save(dtoToRecipe(recipeDto));
		return recipeToDto(recipe);
	}

	@Override
	public RecipeDto updateRecipe(RecipeDto recipeDto) {
		Recipe recipe = recipeRepo.findById(recipeDto.getId()).get();
		recipe.setName(recipeDto.getName());
		recipe.setDescription(recipeDto.getDescription());
		recipe.setIngredients(recipeDto.getIngredients());
		recipe.setInstructions(recipeDto.getInstructions());
		RecipeDto updateRecipeDto = recipeToDto(recipeRepo.save(recipe));
		return updateRecipeDto;
	}

	@Override
	public RecipeDto findById(int recipeId) {
		RecipeDto recipeDto = recipeToDto(recipeRepo.findById(recipeId).get());
		return recipeDto;
	}

	@Override
	public List<RecipeDto> findAll() {
		List<Recipe> recipes = new ArrayList<>();
		List<RecipeDto> recipeDtos = new ArrayList<>();
		recipes = recipeRepo.findAll();
		for(int i=0;i<recipes.size();i++) {
			recipeDtos.add(recipeToDto(recipes.get(i)));
		}
		return recipeDtos;
	}

	@Override
	public String deleteRecipe(int recipeId) {
		recipeRepo.deleteById(recipeId);
		return "Selected recipe has been deleted.";
	}

	private Recipe dtoToRecipe(RecipeDto recipeDto) {
		Recipe recipe = modelMapper.map(recipeDto, Recipe.class);
		return recipe;
	}
	
	private RecipeDto recipeToDto(Recipe recipe) {
		RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
		return recipeDto;
	}
	
}
