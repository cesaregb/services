package com.il.sod.db.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.dao.IEmployeeDAO;
import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.repositories.EmployeeRepository;

@Service
public class EmployeeDAO implements IEmployeeDAO{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> findByEmail(String email){
		return employeeRepository.findByEmail(email);
	}

}
