package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.TaskType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskTypeRepository extends DeletableRepository<TaskType, Integer> {

  @Query("SELECT t FROM TaskType t WHERE t.ordersOnly=:ordersOnly")
  public List<TaskType> findBySection(@Param("ordersOnly") boolean ordersOnly);
}