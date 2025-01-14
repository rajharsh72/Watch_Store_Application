package com.nagarro.bfsitraining.watchstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nagarro.bfsitraining.watchstore.dto.ErrorResponse;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
		//handle all the exceptions
		@ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleException(Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
		
		//handle all the not-found-exceptions
		@ExceptionHandler(NotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
		
		//handle all the exceptions
		@ExceptionHandler(CartException.class)
	    public ResponseEntity<ErrorResponse> handleCartException(CartException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
		
		//handle all the exceptions
		@ExceptionHandler(OrderException.class)
	    public ResponseEntity<ErrorResponse> handleOrderException(OrderException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
		
		@ExceptionHandler(SignatureException.class)
		public ResponseEntity<ErrorResponse> handleSignatureException(OrderException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
	    }

}
