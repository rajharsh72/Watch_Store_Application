package com.nagarro.bfsitraining.watchstore.service.impl;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.RoleDao;
import com.nagarro.bfsitraining.watchstore.dao.UserDao;
import com.nagarro.bfsitraining.watchstore.dto.UserDto;
import com.nagarro.bfsitraining.watchstore.model.Role;
import com.nagarro.bfsitraining.watchstore.model.User;
import com.nagarro.bfsitraining.watchstore.service.RegisterUserService;

import jakarta.transaction.Transactional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Transactional
	@Override
	public String registerUser(UserDto userDto) {	
		User tmp = userDao.findByUsername(userDto.getUsername());
		if(tmp != null) {
			return "User already present...!!";
		}
		
		Set<Role> roles = new HashSet<>();
		Role role = this.roleDao.findById(2L).get();
		roles.add(role);
		
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setRoles(roles);
		user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		user.setMobileNumber(userDto.getMobileNumber());
		user.setAddress(userDto.getAddress());
		
		User savedUser = this.userDao.save(user);
		
		
		return "User addedd successfully...!!!";
	}
}
