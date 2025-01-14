package com.nagarro.bfsitraining.watchstore.service;

import java.util.Optional;

import com.nagarro.bfsitraining.watchstore.model.Watch;

public interface UpdateStockService {

	Optional<Watch> updateStockStatus(Long watchId);

	void updateStockAfterOrder(Long watchId);

}
