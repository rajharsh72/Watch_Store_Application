package com.nagarro.bfsitraining.watchstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.bfsitraining.watchstore.model.Cart;
import com.nagarro.bfsitraining.watchstore.model.User;
import com.nagarro.bfsitraining.watchstore.model.Watch;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
	
	List<Cart> findAllByUser(User user);

	Optional<Cart> findByUser(User user);

	Optional<Cart> findByWatch(Watch watch);

}
