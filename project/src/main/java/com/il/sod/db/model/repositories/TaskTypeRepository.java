package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
	
	@Query("SELECT t FROM TaskType t WHERE t.ordersOnly=:ordersOnly")
    public List<TaskType> findBySection(@Param("ordersOnly") boolean ordersOnly);
}