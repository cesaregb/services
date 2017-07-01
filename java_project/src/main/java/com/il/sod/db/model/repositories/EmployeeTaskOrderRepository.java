package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.EmployeeTaskOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTaskOrderRepository extends JpaRepository<EmployeeTaskOrder, Integer> {
}