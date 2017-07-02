package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the EmployeeType database table.
 */
@Entity
@NamedQuery(name = "EmployeeType.findAll", query = "SELECT e FROM EmployeeType e")
public class EmployeeType extends SoftDeleteEntity implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idEmployeeType;

  private String description;

  private String name;

  //bi-directional many-to-one association to Employee
  @OneToMany(mappedBy = "employeeType", fetch = FetchType.EAGER)
  private Set<Employee> employees;

  public EmployeeType() {
  }

  public int getIdEmployeeType() {
    return this.idEmployeeType;
  }

  public void setIdEmployeeType(int idEmployeeType) {
    this.idEmployeeType = idEmployeeType;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public Employee addEmployee(Employee employee) {
    getEmployees().add(employee);
    employee.setEmployeeType(this);

    return employee;
  }

  public Employee removeEmployee(Employee employee) {
    getEmployees().remove(employee);
    employee.setEmployeeType(null);

    return employee;
  }

  @Override
  public Integer getId() {
    return this.idEmployeeType;
  }

  @Override
  public EmployeeType setId(Integer id) {
    this.idEmployeeType = id;
    return this;
  }
}
