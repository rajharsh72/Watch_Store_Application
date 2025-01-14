package com.nagarro.bfsitraining.watchstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.AddWatchService;

@RestController
@RequestMapping("/api/watch")
public class AddWatchController {
	
	@Autowired
	private AddWatchService addWatchService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Watch> addWatch(@RequestBody Watch watch){
		
		Watch addedWatch = this.addWatchService.addWatch(watch);
		
		return ResponseEntity.ok().body(addedWatch);
		
	}
}
