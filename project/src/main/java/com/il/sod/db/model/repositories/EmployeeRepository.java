package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends DeletableRepository<Employee, Integer> {
	
	List<Employee> findByEmail(String email);
	
}