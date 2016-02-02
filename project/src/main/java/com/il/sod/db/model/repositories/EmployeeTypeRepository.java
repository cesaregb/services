package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.EmployeeType;

public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {}