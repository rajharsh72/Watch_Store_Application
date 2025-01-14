package com.nagarro.bfsitraining.watchstore.dto;

import java.time.LocalDate;
import java.util.List;

import com.nagarro.bfsitraining.watchstore.model.Address;

public class OrderDetailsDto {
	
	public List<OrderItemDto> orderItems;
	public Long orderId;
	public Double totalPrice;
	public LocalDate createdAt;
	public String orderStatus;
	public Address address;
	
	/**
	 * @param orderItems
	 * @param orderId
	 * @param totalPrice
	 * @param createdAt
	 * @param orderStatus
	 * @param address
	 */
	public OrderDetailsDto(List<OrderItemDto> orderItems, Long orderId, Double totalPrice, LocalDate createdAt,
			String orderStatus, Address address) {
		this.orderItems = orderItems;
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.address = address;
	}


	public OrderDetailsDto() {
		
	}
	


	/**
	 * @return the orderItems
	 */
	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}


	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}


	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}


	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}


	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	/**
	 * @return the createdAt
	 */
	public LocalDate getCreatedAt() {
		return createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}


	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}


	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
