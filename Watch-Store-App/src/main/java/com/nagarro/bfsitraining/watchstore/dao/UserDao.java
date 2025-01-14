package com.nagarro.bfsitraining.watchstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.bfsitraining.watchstore.model.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	
	User findByUsername(String username);

}
