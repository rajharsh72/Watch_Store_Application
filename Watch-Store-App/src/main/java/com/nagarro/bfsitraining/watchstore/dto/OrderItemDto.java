package com.nagarro.bfsitraining.watchstore.dto;

import com.nagarro.bfsitraining.watchstore.model.Watch;

public class OrderItemDto {
	
	private Watch watch;
	private Double price;
	private int quantity;
	
	public OrderItemDto() {
		
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
