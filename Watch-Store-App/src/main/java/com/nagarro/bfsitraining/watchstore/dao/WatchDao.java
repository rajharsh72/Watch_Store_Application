package com.nagarro.bfsitraining.watchstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.bfsitraining.watchstore.model.Watch;

@Repository
public interface WatchDao extends JpaRepository<Watch, Long> {
	
	@Modifying
	@Query("UPDATE Watch w SET w.quantity = w.quantity-1, w.inStock = (w.quantity>0) WHERE w.watchId = :watchId")
	void updateStockAfterOrder(Long watchId);

}
