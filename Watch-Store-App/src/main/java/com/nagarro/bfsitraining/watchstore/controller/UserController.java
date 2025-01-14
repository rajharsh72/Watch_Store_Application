package com.nagarro.bfsitraining.watchstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.dto.UserDto;
import com.nagarro.bfsitraining.watchstore.service.RegisterUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private RegisterUserService registerUserService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
		String res = this.registerUserService.registerUser(userDto);
		
		return ResponseEntity.ok().body(res);
	}
	
}
