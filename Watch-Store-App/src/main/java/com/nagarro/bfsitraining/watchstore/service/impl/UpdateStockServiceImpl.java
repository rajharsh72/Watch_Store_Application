package com.nagarro.bfsitraining.watchstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.UpdateStockService;

import jakarta.transaction.Transactional;

@Service
public class UpdateStockServiceImpl implements UpdateStockService {
	
	@Autowired
	private WatchDao watchDao;
	
	@Transactional
	@Override
	public Optional<Watch> updateStockStatus(Long watchId) {
		Optional<Watch> watch = this.watchDao.findById(watchId);
		if(watch.isPresent()) {
			watch.get().setInStock(false);
			watch.get().setQuantity(0);
			
			return Optional.of(this.watchDao.save(watch.get()));
		}
		return Optional.empty();
	}
	
	@Transactional
	@Override
	public void updateStockAfterOrder(Long watchId) {
		this.watchDao.updateStockAfterOrder(watchId);
	}
}
