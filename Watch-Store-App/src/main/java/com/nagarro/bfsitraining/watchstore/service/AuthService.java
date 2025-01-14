package com.nagarro.bfsitraining.watchstore.service;

import com.nagarro.bfsitraining.watchstore.dto.JwtRequest;
import com.nagarro.bfsitraining.watchstore.dto.JwtResponse;

public interface AuthService {

	/**
	 * Method that generates a jwt token used for authorization headers
	 * @param jwtRequest
	 * @return JwtToken assigned to the user
	 * @throws Exception
	 */
	JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;

}
