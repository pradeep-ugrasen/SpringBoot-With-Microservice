package com.javabuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {
	
	private static final String CARD_PAYMENT_SERVICE ="card-payment-service";
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/testcircuitbrk")
	@CircuitBreaker(name = CARD_PAYMENT_SERVICE, fallbackMethod = "orderFallback")
	public String callCircuitBreaker(){
		
		System.out.println("call ==>>");
		String url ="http://localhost:8030/sale";
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
	
	public String orderFallback(Exception e){
		System.out.println("call");
		return "Card-payment service is down, Please try after sometime or call on 9987702294 !";
	}

}
