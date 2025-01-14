package com.nagarro.bfsitraining.watchstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.config.JwtUtil;
import com.nagarro.bfsitraining.watchstore.dao.UserDao;
import com.nagarro.bfsitraining.watchstore.dto.JwtRequest;
import com.nagarro.bfsitraining.watchstore.dto.JwtResponse;
import com.nagarro.bfsitraining.watchstore.model.User;
import com.nagarro.bfsitraining.watchstore.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetails;
	
	/**
	 * Method that generates a jwt token used for authorization headers
	 * @param jwtRequest
	 * @return JwtToken assigned to the user
	 * @throws Exception
	 */
	@Override
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
		String username = jwtRequest.getUsername();
		String userPassword = jwtRequest.getUserPassword();
		this.authenticate(username, userPassword);
		final UserDetails userDetails = this.userDetails.loadUserByUsername(username);
		
		//generating a new token
		String newGeneratedToken = this.jwtUtil.generateToken(userDetails);
		//finding a user based on the email
		User user = this.userDao.findByUsername(username);
		
		//returns a new jwt token for the user
		return new JwtResponse(user, newGeneratedToken);
	}
	
	/**
	 * Method to authenticate the user
	 * @param username
	 * @param userPassword
	 * @throws Exception
	 */
	private void authenticate(String username, String userPassword) throws Exception {
		try {
			
		this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, userPassword));
		}catch(DisabledException e) {
			throw new Exception("User is disabled");
		}catch(BadCredentialsException e) {
			throw new Exception("Bad credentials from user");
			
		}
	}
	

}
