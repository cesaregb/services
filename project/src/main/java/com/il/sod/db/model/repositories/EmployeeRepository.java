package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> { 
	
	List<Employee> findByEmail(String email);
	
}