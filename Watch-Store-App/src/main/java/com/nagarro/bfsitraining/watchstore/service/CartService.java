package com.nagarro.bfsitraining.watchstore.service;

import com.nagarro.bfsitraining.watchstore.dto.AddWatchDto;
import com.nagarro.bfsitraining.watchstore.dto.CartDto;
import com.nagarro.bfsitraining.watchstore.exception.CartException;
import com.nagarro.bfsitraining.watchstore.exception.NotFoundException;
import com.nagarro.bfsitraining.watchstore.model.Cart;

public interface CartService {

	Cart addToCart(String username, AddWatchDto addWatchDto) throws NotFoundException, CartException;

	CartDto getCartByUser(String username) throws NotFoundException;

	void deleteCartItem(Long cartItemId, String username)throws NotFoundException, CartException;

	Cart updateCartQuantity(String username, Long cartId, int newQuantity) throws NotFoundException, CartException ;

}
