package com.nagarro.bfsitraining.watchstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.OrderItemDao;
import com.nagarro.bfsitraining.watchstore.model.Order;
import com.nagarro.bfsitraining.watchstore.model.OrderItem;
import com.nagarro.bfsitraining.watchstore.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public void addOrderItem(OrderItem orderItem) {
		this.orderItemDao.save(orderItem);
	}
	
	@Override
	public List<OrderItem> getOrderItems(Order order){
		
		return this.orderItemDao.findByOrder(order);
	}
}
