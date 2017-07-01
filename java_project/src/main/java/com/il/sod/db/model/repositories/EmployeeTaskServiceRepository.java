package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.EmployeeTaskService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTaskServiceRepository extends JpaRepository<EmployeeTaskService, Integer> {
}