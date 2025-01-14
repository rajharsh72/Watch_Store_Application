package com.nagarro.bfsitraining.watchstore.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.AddWatchService;

import jakarta.transaction.Transactional;

@Service
public class AddWatchServiceImpl implements AddWatchService {
	
	@Autowired
	private WatchDao watchDao;
	
	@Transactional
	@Override
	public Watch addWatch(@RequestBody Watch watch){
		
		watch.setInStock(true);
		if(watch.getQuantity()==0) {
			watch.setQuantity(1);
		}else {
			watch.setQuantity(watch.getQuantity());
		}
		watch.setCreatedAt(LocalDate.now());
		watch.setUpdatedAt(LocalDate.now());
		return this.watchDao.save(watch);
	}
}
