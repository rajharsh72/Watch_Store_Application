package com.nagarro.bfsitraining.watchstore.dto;

import com.nagarro.bfsitraining.watchstore.model.User;

public class JwtResponse {
	
	private User user;			//store the user
	private String jwtToken;	//store the jwtToken
	
	
	//prarameterized constructor
	public JwtResponse(User user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}
	
	
	//getter method to get the User
	public User getUser() {
		return user;
	}

	//setter method to set the User to the class variables
	public void setUser(User user) {
		this.user = user;
	}

	//getter method to get the jwtToken
	public String getJwtToken() {
		return jwtToken;
	}

	//setter method to set the jwtToken to the class variables
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}


}
