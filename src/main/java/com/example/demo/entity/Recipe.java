package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int recipe_id;
	private String name;
	private String description;
	private String ingredients;
	private String instructions;
	private byte[] image;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@Column(name = "category_id", insertable = false, updatable = false)
	private int categoryId;
}
