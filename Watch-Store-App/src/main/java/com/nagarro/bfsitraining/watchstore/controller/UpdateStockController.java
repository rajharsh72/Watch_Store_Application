package com.nagarro.bfsitraining.watchstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.UpdateStockService;

@RestController
@RequestMapping("/api/watch")
public class UpdateStockController {
	
	@Autowired
	private UpdateStockService updateStockService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/stock/{watchId}")
	public ResponseEntity<?> updateStockStatus(@PathVariable Long watchId){
		
		Optional<Watch> watch = this.updateStockService.updateStockStatus(watchId);
		if(watch.isPresent()) {
			return ResponseEntity.ok().body(watch.get());
		}
		return ResponseEntity.status(500).body("Error Occurred ");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/stock/{watchId}")
	public void updateStock(@PathVariable Long watchId){
		
		this.updateStockService.updateStockAfterOrder(watchId);
		
	}
	
	
}
