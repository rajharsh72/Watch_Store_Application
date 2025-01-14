package com.nagarro.bfsitraining.watchstore.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.OrderDao;
import com.nagarro.bfsitraining.watchstore.dao.UserDao;
import com.nagarro.bfsitraining.watchstore.dto.CartDto;
import com.nagarro.bfsitraining.watchstore.dto.CartItemDto;
import com.nagarro.bfsitraining.watchstore.dto.OrderDetailsDto;
import com.nagarro.bfsitraining.watchstore.dto.OrderItemDto;
import com.nagarro.bfsitraining.watchstore.exception.CartException;
import com.nagarro.bfsitraining.watchstore.exception.NotFoundException;
import com.nagarro.bfsitraining.watchstore.exception.OrderException;
import com.nagarro.bfsitraining.watchstore.model.Order;
import com.nagarro.bfsitraining.watchstore.model.OrderItem;
import com.nagarro.bfsitraining.watchstore.model.User;
import com.nagarro.bfsitraining.watchstore.service.CartService;
import com.nagarro.bfsitraining.watchstore.service.OrderItemService;
import com.nagarro.bfsitraining.watchstore.service.OrderService;
import com.nagarro.bfsitraining.watchstore.service.UpdateWatchService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UpdateWatchService updateWatchService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	
	@Transactional
	@Override
	public Order placeOrder(String username) throws NotFoundException, CartException {
		
		User user = this.userDao.findById(username)
						.orElseThrow(() -> new NotFoundException("User not found"));
		
		CartDto cartDto = this.cartService.getCartByUser(username);
		
		
		Order order = new Order();
		
		order.setUser(user);
		order.setCreatedDate(LocalDate.now());
		order.setTotalPrice(cartDto.getTotalPrice());
		order.setOrderStatus("PLACED");
		
		
		Order savedOrder = this.orderDao.save(order);
		List<OrderItem> orderItems = new ArrayList<>();
		
		List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
		for(CartItemDto cartItemDto: cartItemDtoList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(savedOrder);
			orderItem.setWatch(cartItemDto.getWatch());
			orderItem.setQuantity(cartItemDto.getQuantity());
			orderItem.setPrice(cartItemDto.getQuantity() * cartItemDto.getWatch().getPrice());
			orderItem.setCreatedDate(LocalDate.now());
			
			this.updateWatchService.updateWatchQuantityAfterOrder(cartItemDto.getWatch().getWatchId(), cartItemDto.getQuantity());
			this.orderItemService.addOrderItem(orderItem);
			orderItems.add(orderItem);
			this.cartService.deleteCartItem(cartItemDto.getCartItemId(), username);
		}
		
		return savedOrder;
		
	}
	
	
	@Override
	public OrderDetailsDto getOrderDetails(Long orderId) throws NotFoundException {
		
		Order order = this.orderDao.findById(orderId)
							.orElseThrow(() -> new NotFoundException("No order found!!"));
		
		List<OrderItem> orderItemsList = this.orderItemService.getOrderItems(order);
		List<OrderItemDto> orderItemDtoList = new ArrayList<>();
		
		for(OrderItem orderItem: orderItemsList) {
			OrderItemDto orderItemDto = new OrderItemDto();
			orderItemDto.setWatch(orderItem.getWatch());
			orderItemDto.setPrice(orderItem.getPrice());
			orderItemDto.setQuantity(orderItem.getQuantity());
			orderItemDtoList.add(orderItemDto);
		}
		
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
		orderDetailsDto.setOrderItems(orderItemDtoList);
		orderDetailsDto.setOrderId(order.getOrderId());
		orderDetailsDto.setTotalPrice(order.getTotalPrice());
		orderDetailsDto.setOrderStatus(order.getOrderStatus());
		orderDetailsDto.setCreatedAt(order.getCreatedDate());
		orderDetailsDto.setAddress(order.getUser().getAddress());
		
		return orderDetailsDto;
	}
	
	
	@Transactional
	@Override
	public void updateOrderStatus(Long orderId, String newOrderStatus) throws NotFoundException, OrderException {
		
		Order order = this.orderDao.findById(orderId)
				.orElseThrow(() -> new NotFoundException("No order found!!"));
		
		order.setOrderStatus(newOrderStatus);
		
		this.orderDao.save(order);
	}
	
	@Override
	public List<OrderDetailsDto> getAllOrders() throws NotFoundException {
		List<OrderDetailsDto> allOrders = new ArrayList<>();
		List<Order> orders = this.orderDao.findAll();
		
	
		for(Order order: orders) {
			OrderDetailsDto orderDetails = this.getOrderDetails(order.getOrderId());
			allOrders.add(orderDetails);
		}
		
		return allOrders;
	}
	
}
