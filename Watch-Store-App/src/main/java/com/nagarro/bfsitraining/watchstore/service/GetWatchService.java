package com.nagarro.bfsitraining.watchstore.service;

import java.util.List;
import java.util.Optional;

import com.nagarro.bfsitraining.watchstore.model.Watch;

public interface GetWatchService {

	List<Watch> getAllWatches();

	Optional<Watch> getWatchById(Long watchId);

}
