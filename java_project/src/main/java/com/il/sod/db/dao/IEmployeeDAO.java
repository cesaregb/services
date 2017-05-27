package com.il.sod.db.dao;

import java.util.List;

import com.il.sod.db.model.entities.Employee;

public interface IEmployeeDAO {
	
	public abstract List<Employee> findByEmail(String email);
	
}
