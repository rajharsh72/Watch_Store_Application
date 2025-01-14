package com.nagarro.bfsitraining.watchstore.service;

import java.util.List;

import com.nagarro.bfsitraining.watchstore.model.Watch;

public interface SearchWatchService {

	List<Watch> searchWatch(String brand, Double minPrice, Double maxPrice, String type);

}
