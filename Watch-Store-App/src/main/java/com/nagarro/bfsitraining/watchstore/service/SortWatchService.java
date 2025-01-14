package com.nagarro.bfsitraining.watchstore.service;

import java.util.List;

import com.nagarro.bfsitraining.watchstore.model.Watch;

public interface SortWatchService {

	List<Watch> sortBasedOnLatestArrival();

}
