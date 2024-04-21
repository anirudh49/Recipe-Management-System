package com.example.demo.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.FullRecipeDto;
import com.example.demo.payload.SimplRecipeDto;

public interface RecipeBo {

	SimplRecipeDto createRecipe(SimplRecipeDto simplRecipeDto, MultipartFile multipartFile) throws IOException;
	SimplRecipeDto updateRecipe(SimplRecipeDto simplRecipeDto);
	SimplRecipeDto findById(int recipeId);
	List<SimplRecipeDto> findAll();
	String deleteRecipe(int recipeId);
	byte[] findImageDataById(int recipeId) throws IOException;
	List<SimplRecipeDto> findByCategory(int category_id);
}
