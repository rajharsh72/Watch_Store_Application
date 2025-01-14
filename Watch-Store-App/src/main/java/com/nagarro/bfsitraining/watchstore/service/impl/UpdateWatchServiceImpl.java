package com.nagarro.bfsitraining.watchstore.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.UpdateWatchService;

import jakarta.transaction.Transactional;

@Service
public class UpdateWatchServiceImpl implements UpdateWatchService {
	
	@Autowired
	private WatchDao watchDao;
	
	@Transactional
	@Override
	public Optional<Watch> updateWatch(Long watchId, Watch newWatch) {
		Optional<Watch> oldWatch = this.watchDao.findById(watchId);
		if(oldWatch.isPresent()) {
			Watch watch = oldWatch.get();
			
			watch.setBrand(newWatch.getBrand());
			watch.setName(newWatch.getName());
			watch.setDescription(newWatch.getDescription());
			watch.setPrice(newWatch.getPrice());
			watch.setQuantity(newWatch.getQuantity());
			if(watch.getQuantity() >0) {
				watch.setInStock(true);
			}
			watch.setType(newWatch.getType());
//			watch.setInStock(true);
			watch.setImageUrl(newWatch.getImageUrl());
			watch.setUpdatedAt(LocalDate.now());
			
			return Optional.of(this.watchDao.save(watch));
		}
		return Optional.empty();

	}
	
	
	@Transactional
	@Override
	public void updateWatchQuantityAfterOrder(Long watchId, int quantity) {
		Optional<Watch> watch = this.watchDao.findById(watchId);
		if(watch.isPresent()) {
			watch.get().setQuantity(watch.get().getQuantity() - quantity);
			if(watch.get().getQuantity() <=0) {
				watch.get().setInStock(false);
			}
			this.watchDao.save(watch.get());
		}
	}

}
