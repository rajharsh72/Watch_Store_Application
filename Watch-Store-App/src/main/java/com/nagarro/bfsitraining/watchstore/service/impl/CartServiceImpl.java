package com.nagarro.bfsitraining.watchstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.CartDao;
import com.nagarro.bfsitraining.watchstore.dao.UserDao;
import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.dto.AddWatchDto;
import com.nagarro.bfsitraining.watchstore.dto.CartDto;
import com.nagarro.bfsitraining.watchstore.dto.CartItemDto;
import com.nagarro.bfsitraining.watchstore.exception.CartException;
import com.nagarro.bfsitraining.watchstore.exception.NotFoundException;
import com.nagarro.bfsitraining.watchstore.model.Cart;
import com.nagarro.bfsitraining.watchstore.model.User;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.CartService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WatchDao watchDao;
	
	@Transactional
	@Override
	public Cart addToCart(String username, AddWatchDto addWatchDto) throws NotFoundException, CartException {
		
		User user = this.userDao.findById(username)
						.orElseThrow(() -> new NotFoundException("User not found"));
		
		Watch watch = this.watchDao.findById(addWatchDto.getWatchId())
				.orElseThrow(() -> new NotFoundException("Watch not found"));
		
		if(watch.getQuantity() < addWatchDto.getQuantity()) {
			throw new CartException("Quantity exceeds the stock inventory!!");
		}
		
		if(!watch.isInStock()) {
			throw new CartException("Watch out of stock");
		}
		
		Optional<Cart> oldCart =  this.cartDao.findByWatch(watch);
		if(oldCart.isPresent()) {
			throw new CartException("Watch already added to cart");
		}
		
		Cart cart = new Cart();
		cart.setWatch(watch);
		cart.setUser(user);
		cart.setQuantity(addWatchDto.getQuantity());
		
		return this.cartDao.save(cart);
	}
	
	
	@Override
	public CartDto getCartByUser(String username) throws NotFoundException {
		
		User user = this.userDao.findById(username)
				.orElseThrow(() -> new NotFoundException("User not found"));
		
		List<Cart> cartsByUser = this.cartDao.findAllByUser(user);
		
		List<CartItemDto> cartItems = new ArrayList<>();
		Double totalCost = 0.0;
		for(Cart cart: cartsByUser) {
			CartItemDto cartItemDto = new CartItemDto();
			cartItemDto.setCartItemId(cart.getCartId());
			cartItemDto.setQuantity(cart.getQuantity());
			cartItemDto.setWatch(cart.getWatch());
			totalCost += cartItemDto.getQuantity() * cart.getWatch().getPrice();
			cartItems.add(cartItemDto);
		}
		
		CartDto cartDto = new CartDto();
		cartDto.setTotalPrice(totalCost);
		cartDto.setCartItems(cartItems);
		
		return cartDto;
	}
	
	
	@Override
	public void deleteCartItem(Long cartItemId, String username) throws NotFoundException, CartException {
		
		User user = this.userDao.findById(username)
				.orElseThrow(() -> new NotFoundException("User not found"));
		
		Optional<Cart> cart = this.cartDao.findById(cartItemId);
					
		if(cart.isEmpty()) {
			throw new CartException("Invalid Cart Item");
			
		}
		if(cart.get().getUser().getUsername() != username) {
			throw new CartException("Cart item does not belong to user");
		}
		
		this.cartDao.delete(cart.get());
		
	}
	
	@Transactional
	@Override
	public Cart updateCartQuantity(String username, Long cartId, int newQuantity) throws NotFoundException, CartException  {
		User user = this.userDao.findById(username)
				.orElseThrow(() -> new NotFoundException("User not found"));
		
		Cart cart = this.cartDao.findById(cartId)
							.orElseThrow(() -> new NotFoundException("Cart not found"));
		
		if(!cart.getUser().getUsername().equals(username)) {
			throw new CartException("Cart does not belong to the user");
		}
		
		if(cart.getWatch().getQuantity() > newQuantity && cart.getWatch().isInStock()) {
			cart.setQuantity(newQuantity);
			return this.cartDao.save(cart);
		}
		
		return null;
	}

}
