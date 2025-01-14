package com.nagarro.bfsitraining.watchstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.UpdateWatchService;

@RestController
@RequestMapping("/api/watch")
public class UpdateWatchController {
	
	@Autowired
	private UpdateWatchService updateWatchService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{watchId}")
	public ResponseEntity<?> updateWatch(@PathVariable Long watchId,
											@RequestBody Watch watch){
		
		Optional<Watch> updatedWatch = this.updateWatchService.updateWatch(watchId, watch);
		
		if(updatedWatch.isPresent()) {
			return ResponseEntity.ok().body(updatedWatch.get());
		}
		return ResponseEntity.status(404).body("No watch found with the given ID ");
		
	}

}
