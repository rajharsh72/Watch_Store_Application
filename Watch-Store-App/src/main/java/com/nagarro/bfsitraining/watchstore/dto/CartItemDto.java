package com.nagarro.bfsitraining.watchstore.dto;

import com.nagarro.bfsitraining.watchstore.model.Watch;

public class CartItemDto {
	private Long cartItemId;
	private int quantity;
	private Watch watch;
	
	public CartItemDto() {
		
	}
	
	
	/**
	 * @return the cartItemId
	 */
	public Long getCartItemId() {
		return cartItemId;
	}
	/**
	 * @param cartItemId the cartItemId to set
	 */
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
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
	
	

}
