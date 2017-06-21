package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.il.sod.db.model.entities.DistanceInfo;

public interface DistanceInfoRepository extends JpaRepository<DistanceInfo, Integer> {
	
	@Query("SELECT d FROM DistanceInfo d ORDER BY distance DESC")
	public List<DistanceInfo> findAllOrderByDistance();
	
}