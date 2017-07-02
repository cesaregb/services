package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.DistanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistanceInfoRepository extends JpaRepository<DistanceInfo, Integer> {

  @Query("SELECT d FROM DistanceInfo d ORDER BY distance DESC")
  public List<DistanceInfo> findAllOrderByDistance();

}