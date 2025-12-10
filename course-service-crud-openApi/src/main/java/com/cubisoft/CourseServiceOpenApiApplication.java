package com.cubisoft;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "COURSE SERVICE", version = "1.0", description = "Course Api Crud Operation"))
public class CourseServiceOpenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceOpenApiApplication.class, args);
	}

}
