package com.javabuster.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EMIEnquiryController {

	@GetMapping("/emiEnquiry")
	public ResponseEntity<?> emiEnquiry(){
		
		return ResponseEntity.ok("EMI Allowed on this card,Please proceed ! ");
	}
}
