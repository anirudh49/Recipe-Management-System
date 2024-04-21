package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int category_id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "category")
	private List<Recipe> recipies = new ArrayList<>();
}
