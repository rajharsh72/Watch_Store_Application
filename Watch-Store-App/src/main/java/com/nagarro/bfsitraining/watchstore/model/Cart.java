package com.nagarro.bfsitraining.watchstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	@ManyToOne
	@JoinColumn(name="watch_id")
	private Watch watch;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	private int quantity;
	
	public Cart() {
		
	}

	/**
	 * @param cartId
	 * @param watch
	 * @param user
	 * @param quantity
	 */
	public Cart(Long cartId, Watch watch, User user, int quantity) {
		this.cartId = cartId;
		this.watch = watch;
		this.user = user;
		this.quantity = quantity;
	}



	/**
	 * @return the cartId
	 */
	public Long getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
