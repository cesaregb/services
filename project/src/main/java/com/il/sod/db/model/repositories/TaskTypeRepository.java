package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {}