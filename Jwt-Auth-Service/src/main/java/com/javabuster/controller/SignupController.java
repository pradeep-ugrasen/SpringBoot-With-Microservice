package com.javabuster.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javabuster.dao.RoleRepository;
import com.javabuster.dao.UserRepository;
import com.javabuster.model.Role;
import com.javabuster.model.Roles;
import com.javabuster.model.SignupRequest;
import com.javabuster.model.User;

@RestController
@RequestMapping("/auth")
public class SignupController {
	
	@Autowired
	UserRepository userRepository;
  
	@Autowired
	RoleRepository roleRepository;
  
	@Autowired
	PasswordEncoder encoder;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody SignupRequest signupRequest) {
		
		System.out.println("User Sign-up Controller call !");
		
		if(userRepository.existsByUserName(signupRequest.getUserName())){
			return ResponseEntity.badRequest().body("Username is already taken");
		}
		  
		if(userRepository.existsByEmail(signupRequest.getEmail())){
			return ResponseEntity.badRequest().body("Email is already taken");
		}
		  
		User user = new User();
		Set<Role> roles = new HashSet<>();
		
		user.setUserName(signupRequest.getUserName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(encoder.encode(signupRequest.getPassword()));
	    
		System.out.println("Encoded password--- " + user.getPassword());
		String[] roleArr = signupRequest.getRoles();
	    
		if(roleArr == null) {
			roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get());
		}
		  
		for(String role: roleArr) {
			switch(role) {
				case "admin":
			  		roles.add(roleRepository.findByRoleName(Roles.ROLE_ADMIN).get());
			  		break;
			  	case "user":
			  		roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get());
			  		break;  
			  	default:
			  		return ResponseEntity.badRequest().body("Specified role not found");
			  }
		  }
		
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok("User signed up successfully");
	}
}
