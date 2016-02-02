package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.EmployeeTaskOrder;

public interface EmployeeTaskOrderRepository extends JpaRepository<EmployeeTaskOrder, Integer> {}