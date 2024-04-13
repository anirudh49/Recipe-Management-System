package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bo.RecipeBo;
import com.example.demo.payload.FullRecipeDto;
import com.example.demo.payload.SimplRecipeDto;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	RecipeBo recipeBo;

	@PostMapping("/create")
	SimplRecipeDto createRecipe(@RequestPart(value = "SimplRecipeDto", required = true) SimplRecipeDto simplRecipeDto, @RequestPart(value = "image", required = false) MultipartFile multipartFile ) throws IOException {
		return recipeBo.createRecipe(simplRecipeDto, multipartFile);
	}

	@PutMapping("/update")
	SimplRecipeDto updateRecipe(@RequestBody SimplRecipeDto simplRecipeDto) {
		return recipeBo.updateRecipe(simplRecipeDto);
	}

	@GetMapping("/findById/{recipeId}")
	SimplRecipeDto findById(@PathVariable("recipeId") int recipeId) {
		return recipeBo.findById(recipeId);
	}

	@GetMapping("/findAll")
	List<SimplRecipeDto> findAll() {
		return recipeBo.findAll();
	}

	@DeleteMapping("/delete/{recipeId}")
	String deleteRecipe(@PathVariable("recipeId") int recipeId) {
		return recipeBo.deleteRecipe(recipeId);
	}
	
	@GetMapping("/getImage/{recipeId}")
	ResponseEntity<?> findImageById(@PathVariable int recipeId) throws IOException{
		byte[] imageData = recipeBo.findImageDataById(recipeId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}
}
