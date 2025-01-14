package com.nagarro.bfsitraining.watchstore.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="order_items")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	
	@ManyToOne
	@JoinColumn(name="order_id", referencedColumnName="orderId")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="watch_id", referencedColumnName="watchId")
	private Watch watch;
	
	private LocalDate createdDate;
	private Double price;
	private int quantity;
	
	public OrderItem() {
		
	}
	
	
	/**
	 * @param orderItemId
	 * @param order
	 * @param watch
	 * @param createdDate
	 * @param price
	 * @param quantity
	 */
	public OrderItem(Long orderItemId, Order order, Watch watch, LocalDate createdDate, Double price, int quantity) {
		this.orderItemId = orderItemId;
		this.order = order;
		this.watch = watch;
		this.createdDate = createdDate;
		this.price = price;
		this.quantity = quantity;
	}
	/**
	 * @return the orderItemId
	 */
	public Long getOrderItemId() {
		return orderItemId;
	}
	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 * @return the watch
	 */
	public Watch getWatch() {
		return watch;
	}
	/**
	 * @param watch the watch to set
	 */
	public void setWatch(Watch watch) {
		this.watch = watch;
	}
	/**
	 * @return the createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	

}
