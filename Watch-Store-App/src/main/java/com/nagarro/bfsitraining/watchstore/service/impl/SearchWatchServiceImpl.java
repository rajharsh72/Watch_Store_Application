package com.nagarro.bfsitraining.watchstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.SearchWatchService;

@Service
public class SearchWatchServiceImpl implements SearchWatchService {
	
	@Autowired
	private WatchDao watchDao;
	
	@Override
	public List<Watch> searchWatch(String brand, Double minPrice, Double maxPrice, String type){
		
		List<Watch> watches = this.watchDao.findAll();
		
		
		return watches.stream()
				.filter(watch -> brand == null  || watch.getBrand().equalsIgnoreCase(brand))
				.filter(watch -> minPrice == null || watch.getPrice() >= minPrice)
				.filter(watch -> maxPrice == null || watch.getPrice() <= maxPrice)
				.filter(watch -> type == null || watch.getType().equalsIgnoreCase(type))
				.collect(Collectors.toList());
		
	}
}
