package com.il.sod.db.dao;

import com.il.sod.db.model.entities.Employee;

import java.util.List;

public interface IEmployeeDAO {

  public abstract List<Employee> findByEmail(String email);

}
