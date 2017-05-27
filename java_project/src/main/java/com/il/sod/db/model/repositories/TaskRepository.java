package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends DeletableRepository<Task, Integer> {
	
	@Query("SELECT t FROM Task t WHERE t.taskType.idTaskType=:taskType")
    public List<Task> findByTaskType(@Param("taskType") Integer taskType);
	
	
}