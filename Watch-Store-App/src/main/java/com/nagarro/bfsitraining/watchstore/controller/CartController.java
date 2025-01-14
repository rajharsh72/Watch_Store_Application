package com.nagarro.bfsitraining.watchstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.dto.AddWatchDto;
import com.nagarro.bfsitraining.watchstore.dto.ApiResponse;
import com.nagarro.bfsitraining.watchstore.dto.CartDto;
import com.nagarro.bfsitraining.watchstore.exception.CartException;
import com.nagarro.bfsitraining.watchstore.exception.NotFoundException;
import com.nagarro.bfsitraining.watchstore.model.Cart;
import com.nagarro.bfsitraining.watchstore.service.CartService;

@RestController
@RequestMapping("/api")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/cart/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddWatchDto addWatchDto) throws NotFoundException, CartException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Cart cart = this.cartService.addToCart(username, addWatchDto);
		if(cart!=null) {
			ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), "Item added to cart");
			return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Error adding item in cart"));
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/cart")
	public ResponseEntity<CartDto> getCartByUser() throws NotFoundException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		CartDto cartDto = this.cartService.getCartByUser(username);
		return ResponseEntity.ok().body(cartDto);
	}
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/cart/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId) throws NotFoundException, CartException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		this.cartService.deleteCartItem(cartItemId, username );
		ApiResponse apiResponse = new ApiResponse(HttpStatus.ACCEPTED.value(), "Item removed from cart");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);

	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/cart/update/{cartItemId}")
	public ResponseEntity<ApiResponse> updateCartQuantity(@PathVariable Long cartItemId,
												@RequestParam int quantity) throws NotFoundException, CartException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Cart cart = this.cartService.updateCartQuantity(username, cartItemId, quantity);
		if(cart!=null) {
			ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), "Item updated in cart");
			return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Error updating item in cart"));
		
		
	}
	
	
}
