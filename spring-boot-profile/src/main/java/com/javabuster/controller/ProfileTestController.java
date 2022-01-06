package com.javabuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javabuster.service.ProfileService;

@RestController
public class ProfileTestController {

	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/test-profile")
	public ResponseEntity<?> testProfile(){
		
		String str = profileService.testProfile();
		
		return ResponseEntity.ok(str);
	}
}
