package com.il.sod.db.dao.impl;

import com.il.sod.db.dao.IEmployeeDAO;
import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDAO implements IEmployeeDAO {

  @Autowired
  EmployeeRepository employeeRepository;

  public List<Employee> findByEmail(String email) {
    return employeeRepository.findByEmail(email);
  }

}
