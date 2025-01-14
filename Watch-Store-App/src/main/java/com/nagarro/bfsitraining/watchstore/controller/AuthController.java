package com.nagarro.bfsitraining.watchstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.dto.JwtRequest;
import com.nagarro.bfsitraining.watchstore.dto.JwtResponse;
import com.nagarro.bfsitraining.watchstore.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception{
		return this.authService.createJwtToken(jwtRequest);
	}
	
	
}
