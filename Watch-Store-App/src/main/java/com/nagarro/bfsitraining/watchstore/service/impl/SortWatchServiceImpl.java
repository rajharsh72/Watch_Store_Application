package com.nagarro.bfsitraining.watchstore.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.bfsitraining.watchstore.dao.WatchDao;
import com.nagarro.bfsitraining.watchstore.model.Watch;
import com.nagarro.bfsitraining.watchstore.service.SortWatchService;

@Service
public class SortWatchServiceImpl implements SortWatchService {
	
	@Autowired
	private WatchDao watchDao;
	
	@Override
	public List<Watch> sortBasedOnLatestArrival(){
		
		List<Watch> watches = this.watchDao.findAll();
		
		return watches.stream()
					.sorted(Comparator.comparing(Watch::getCreatedAt).reversed())
					.collect(Collectors.toList());
	}
}
