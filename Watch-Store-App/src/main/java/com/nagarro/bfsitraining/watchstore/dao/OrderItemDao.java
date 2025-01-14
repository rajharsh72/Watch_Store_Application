package com.nagarro.bfsitraining.watchstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.bfsitraining.watchstore.model.Order;
import com.nagarro.bfsitraining.watchstore.model.OrderItem;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

	
	List<OrderItem> findByOrder(Order order);

}
