package com.javabuster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book API", version = "1.0", description = "Book Details"))
public class OpenApiDocumentationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenApiDocumentationApplication.class, args);
	}

}
