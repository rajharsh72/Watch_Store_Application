package com.nagarro.bfsitraining.watchstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.GetWatchService;
import com.nagarro.bfsitraining.watchstore.service.SearchWatchService;
import com.nagarro.bfsitraining.watchstore.service.SortWatchService;

@RestController
@RequestMapping("/api/watch")
public class GetWatchController {

	@Autowired
	private GetWatchService getWatchService;
	
	@Autowired
	private SearchWatchService searchWatchService;
	
	@Autowired
	private SortWatchService sortWatchService;
	
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping
	public ResponseEntity<List<Watch>> getAllWatches(){
		List<Watch> watches = this.getWatchService.getAllWatches();
		
		return ResponseEntity.ok().body(watches);
	}
	
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/{watchId}")
	public ResponseEntity<?> getWatchById(@PathVariable Long watchId){
		Optional<Watch> watch = this.getWatchService.getWatchById(watchId);
		if(watch.isPresent()) {
			return ResponseEntity.ok().body(watch);
		}
		return ResponseEntity.status(404).body(watch);
		
	}
	
	
	
	
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/sort")
	public ResponseEntity<List<Watch>> getSortedWatches(){
		List<Watch> watches = this.sortWatchService.sortBasedOnLatestArrival();
		
		return ResponseEntity.ok().body(watches);
	}
	
	
	
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/search")
	public ResponseEntity<List<Watch>> getSearchedWatch(@RequestParam(required=false) String brand,
											@RequestParam(required=false) Double minPrice,
											@RequestParam(required=false) Double maxPrice,
											@RequestParam(required=false) String type){
		
		List<Watch> watches = this.searchWatchService.searchWatch(brand, minPrice, maxPrice, type);
		
		if(watches.isEmpty()) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok().body(watches);
		
	}
}
