package com.javabuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PurchaseController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/sale")
	public ResponseEntity<?> purchaseTransaction(){
		
		return ResponseEntity.ok("Sale Transaction Done"+env.getProperty("local.server.port"));	
	}
	
	@GetMapping("/communication")
	public ResponseEntity<?> testMicroServiceCommunication(){
		
		//Service Call
		String url = "http://localhost:8040/emiEnquiry";
		String result = restTemplate.getForObject(url, String.class);
		
		return ResponseEntity.ok(result);
	}

}
