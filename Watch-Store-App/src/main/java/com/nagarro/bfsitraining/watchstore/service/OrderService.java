package com.nagarro.bfsitraining.watchstore.service;

import java.util.List;

import com.nagarro.bfsitraining.watchstore.dto.OrderDetailsDto;
import com.nagarro.bfsitraining.watchstore.exception.CartException;
import com.nagarro.bfsitraining.watchstore.exception.NotFoundException;
import com.nagarro.bfsitraining.watchstore.exception.OrderException;
import com.nagarro.bfsitraining.watchstore.model.Order;

public interface OrderService {

	Order placeOrder(String username) throws NotFoundException, CartException;

	OrderDetailsDto getOrderDetails(Long orderId) throws NotFoundException;

	List<OrderDetailsDto> getAllOrders() throws NotFoundException;

	void updateOrderStatus(Long orderId, String newOrderStatus) throws NotFoundException, OrderException;

}
