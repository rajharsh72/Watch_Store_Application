package com.nagarro.bfsitraining.watchstore.dto;

public class AddWatchDto {
	
	private Long watchId;
	private int quantity;
	
	public AddWatchDto() {
		
	}
	
	/**
	 * @return the watchId
	 */
	public Long getWatchId() {
		return watchId;
	}
	/**
	 * @param watchId the watchId to set
	 */
	public void setWatchId(Long watchId) {
		this.watchId = watchId;
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
