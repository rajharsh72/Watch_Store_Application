 package com.nagarro.bfsitraining.watchstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.GetWatchService;

@Service
public class GetWatchServiceImpl implements GetWatchService {
	
	@Autowired
	private WatchDao watchDao;
	
	@Override
	public List<Watch> getAllWatches(){
		return this.watchDao.findAll();
	}
	
	@Override
	public Optional<Watch> getWatchById(Long watchId){
		
		return this.watchDao.findById(watchId);
	}
}
