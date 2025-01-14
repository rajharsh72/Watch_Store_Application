package com.nagarro.bfsitraining.watchstore.service;

import java.util.Optional;

import com.nagarro.bfsitraining.watchstore.model.Watch;

public interface UpdateWatchService {

	Optional<Watch> updateWatch(Long watchId, Watch newWatch);

	void updateWatchQuantityAfterOrder(Long watchId, int quantity);

}
