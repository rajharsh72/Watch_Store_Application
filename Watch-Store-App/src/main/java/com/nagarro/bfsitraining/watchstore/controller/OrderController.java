package com.nagarro.bfsitraining.watchstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.dto.ApiResponse;
import com.nagarro.bfsitraining.watchstore.dto.OrderDetailsDto;
import com.nagarro.bfsitraining.watchstore.exception.CartException;
import com.nagarro.bfsitraining.watchstore.exception.NotFoundException;
import com.nagarro.bfsitraining.watchstore.exception.OrderException;
import com.nagarro.bfsitraining.watchstore.model.Order;
import com.nagarro.bfsitraining.watchstore.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/place-order")
	public ResponseEntity<Order> placeOrder() throws NotFoundException, CartException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Order order = this.orderService.placeOrder(username);
		return ResponseEntity.ok().body(order);
	}
	
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable Long orderId) throws NotFoundException{
		
		OrderDetailsDto orderDetails = this.orderService.getOrderDetails(orderId);
		
		return ResponseEntity.ok().body(orderDetails);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/order/{orderId}")
	public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable Long orderId, @RequestParam String newOrderStatus) throws NotFoundException, OrderException{
		
		this.orderService.updateOrderStatus(orderId, newOrderStatus);
		
		ApiResponse apiResponse = new ApiResponse(HttpStatus.ACCEPTED.value(), "Order Status Updated Successfully");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/order")
	public ResponseEntity<List<OrderDetailsDto>> getAllOrders() throws NotFoundException{
		
		List<OrderDetailsDto> allOrders = this.orderService.getAllOrders();
		return ResponseEntity.ok().body(allOrders);
	}
}
