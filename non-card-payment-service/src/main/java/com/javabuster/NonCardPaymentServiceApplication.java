package com.javabuster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NonCardPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NonCardPaymentServiceApplication.class, args);
	}

}
