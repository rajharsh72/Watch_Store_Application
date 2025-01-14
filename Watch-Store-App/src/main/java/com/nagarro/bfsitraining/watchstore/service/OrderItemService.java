package com.nagarro.bfsitraining.watchstore.service;

import java.util.List;

import com.nagarro.bfsitraining.watchstore.model.Order;
import com.nagarro.bfsitraining.watchstore.model.OrderItem;

public interface OrderItemService {

	void addOrderItem(OrderItem orderItem);

	
	List<OrderItem> getOrderItems(Order order);

}
