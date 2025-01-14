package com.nagarro.bfsitraining.watchstore.dto;

import java.util.List;

public class CartDto {
	
	private List<CartItemDto> cartItems;
	private Double totalPrice;
	
	public CartDto() {
		
	}
	
	
	/**
	 * @return the cartItems
	 */
	public List<CartItemDto> getCartItems() {
		return cartItems;
	}
	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
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
	
	
}
