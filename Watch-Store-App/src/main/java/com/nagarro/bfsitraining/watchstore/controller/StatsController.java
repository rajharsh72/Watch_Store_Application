package com.nagarro.bfsitraining.watchstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.service.StatsService;

@RestController
@RequestMapping("/api/admin")
public class StatsController {
	
	@Autowired
	private StatsService statsService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/stock-stats")
	public ResponseEntity<Map<String, Long>> getStockStats(){
		
		return ResponseEntity.ok().body(this.statsService.stockStats());
	}
}
