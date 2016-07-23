package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	@Query("SELECT t FROM Task t WHERE t.taskType.idTaskType=:taskType")
    public List<Task> findByTaskType(@Param("taskType") Integer taskType);
	
	
}