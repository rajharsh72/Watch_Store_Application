package com.nagarro.bfsitraining.watchstore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {
	
	@Autowired
	private WatchDao watchDao;
	
	
	@Override
	public Map<String, Long> stockStats() {
		
		List<Watch> watches = this.watchDao.findAll();
		
		Long totalWatches = this.watchDao.count();
		
		Long inStockWatches = watches.stream().filter(Watch::isInStock).count();
		
		Long outOfStockWatches = totalWatches - inStockWatches;
		
		Map<String, Long> stockStats = new HashMap<>();
		
		stockStats.put("In Stock", inStockWatches);
		stockStats.put("Out Of Stock", outOfStockWatches);
		
		return stockStats;
		
		
		
	}
	
	
}
