package com.nagarro.bfsitraining.watchstore.service;

import com.nagarro.bfsitraining.watchstore.dto.UserDto;
import com.nagarro.bfsitraining.watchstore.model.User;

public interface RegisterUserService {

	String registerUser(UserDto userDto);

}
