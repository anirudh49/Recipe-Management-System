package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RecipeDto {

	private int id;
	private String name;
	private String description;
	private String ingredients;
	private String instructions;
}
