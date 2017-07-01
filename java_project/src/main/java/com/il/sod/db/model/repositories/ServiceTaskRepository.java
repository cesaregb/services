package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ServiceTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTaskRepository extends JpaRepository<ServiceTask, Integer> {
}