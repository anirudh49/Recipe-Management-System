package com.example.demo.bo.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bo.RecipeBo;
import com.example.demo.entity.Category;
import com.example.demo.entity.Recipe;
import com.example.demo.payload.FullRecipeDto;
import com.example.demo.payload.ImagesUtil;
import com.example.demo.payload.SimplRecipeDto;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.RecipeRepo;

@Service
public class RecipeBoImpl implements RecipeBo {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	RecipeRepo recipeRepo;
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public SimplRecipeDto createRecipe(SimplRecipeDto simplRecipeDto, MultipartFile multipartFile) throws IOException {
		if (multipartFile != null) {
			byte[] imageData = multipartFile.getBytes();
			Category category = categoryRepo.findById(simplRecipeDto.getCategory_id()).get();
			Recipe rcpwithImg = simplDtoToRecipe(simplRecipeDto);
			rcpwithImg.setCategory(category);
			rcpwithImg.setImage(ImagesUtil.compress(imageData));
			Recipe recipe = recipeRepo.save(rcpwithImg);
			return recipeToSimplDto(recipe);
		}
		else {
			Category category = categoryRepo.findById(simplRecipeDto.getCategory_id()).get();
			Recipe recipe = simplDtoToRecipe(simplRecipeDto);
			recipe.setCategory(category);
			Recipe output =  recipeRepo.save(recipe);
			return recipeToSimplDto(output);
		}
		
	}

	@Override
	public SimplRecipeDto updateRecipe(SimplRecipeDto simplRecipeDto) {
		Recipe recipe = recipeRepo.findById(simplRecipeDto.getRecipe_id()).get();
		recipe.setName(simplRecipeDto.getName());
		recipe.setDescription(simplRecipeDto.getDescription());
		recipe.setIngredients(simplRecipeDto.getIngredients());
		recipe.setInstructions(simplRecipeDto.getInstructions());
		Category category = categoryRepo.findById(simplRecipeDto.getCategory_id()).get();
		recipe.setCategory(category);

		SimplRecipeDto updateRecipeDto = recipeToSimplDto(recipeRepo.save(recipe));
		return updateRecipeDto;
	}

	@Override
	public SimplRecipeDto findById(int recipeId) {
		SimplRecipeDto simplRecipeDto = recipeToSimplDto(recipeRepo.findById(recipeId).get());
		return simplRecipeDto;
	}

	@Override
	public List<SimplRecipeDto> findAll() {
		List<Recipe> recipes = new ArrayList<>();
		List<SimplRecipeDto> simplRecipeDtos = new ArrayList<>();
		recipes = recipeRepo.findAll();
		for (int i = 0; i < recipes.size(); i++) {
			simplRecipeDtos.add(recipeToSimplDto(recipes.get(i)));
		}
		return simplRecipeDtos;
	}

	@Override
	public String deleteRecipe(int recipeId) {
		recipeRepo.deleteById(recipeId);
		return "Selected recipe has been deleted.";
	}

	@Override
	public byte[] findImageDataById(int recipeId) throws IOException {
		Recipe recipe = recipeRepo.findById(recipeId).get();
		return ImagesUtil.decompress(recipe.getImage());
	}
	
	@Override
	public List<SimplRecipeDto> findByCategory(int category_id) {
		List<SimplRecipeDto> simplRecipeDtos = new ArrayList<>();
		List<Recipe> recipes = recipeRepo.findByCategoryId(category_id);
		for(int i=0;i<recipes.size();i++) {
			simplRecipeDtos.add(recipeToSimplDto(recipes.get(i)));
		}
		return simplRecipeDtos;
	}

	private Recipe fullDtoToRecipe(FullRecipeDto fullRecipeDto) {
		Recipe recipe = modelMapper.map(fullRecipeDto, Recipe.class);
		return recipe;
	}

	private FullRecipeDto recipeToFullDto(Recipe recipe) {
		FullRecipeDto fullRecipeDto = modelMapper.map(recipe, FullRecipeDto.class);
		return fullRecipeDto;
	}

	private Recipe simplDtoToRecipe(SimplRecipeDto simplRecipeDto) {
		Recipe recipe = modelMapper.map(simplRecipeDto, Recipe.class);
		return recipe;
	}

	private SimplRecipeDto recipeToSimplDto(Recipe recipe) {
		SimplRecipeDto simplRecipeDto = modelMapper.map(recipe, SimplRecipeDto.class);
		return simplRecipeDto;
	}

}
