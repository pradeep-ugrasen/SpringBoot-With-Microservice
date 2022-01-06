package com.javabuster.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javabuster.model.AuthResponse;
import com.javabuster.model.CustomUserBean;
import com.javabuster.model.User;
import com.javabuster.util.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody User user) {
		System.out.println("AuthController -- userLogin");
	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
	    
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String token = jwtTokenUtil.generateJwtToken(authentication);
	    
	    CustomUserBean userBean = (CustomUserBean) authentication.getPrincipal();    
	    List<String> roles = userBean.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
	    
	    AuthResponse authResponse = new AuthResponse();
	    authResponse.setToken(token);
	    authResponse.setRoles(roles);
	    return ResponseEntity.ok(authResponse);
	  }
}
