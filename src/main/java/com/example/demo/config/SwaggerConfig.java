package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Recipe management system",
				description = "Here you can create recipe, manage them & add photos to them.",
				summary = "It is a backend project having APIs of Recipe management system.",
				version = "1.0",
				contact = @Contact(
						email = "customersupport@recipemgmtsys.com")
				)
		)
public class SwaggerConfig {

}
