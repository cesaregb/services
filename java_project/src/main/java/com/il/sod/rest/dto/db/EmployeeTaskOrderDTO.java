package com.il.sod.rest.dto.db;

public class EmployeeTaskOrderDTO {
  private int idEmployeeTaskOrder;
  private String comments;
  private int idEmployee;
  private int idOrderTask;

  public int getIdEmployeeTaskOrder() {
    return idEmployeeTaskOrder;
  }

  public void setIdEmployeeTaskOrder(int idEmployeeTaskOrder) {
    this.idEmployeeTaskOrder = idEmployeeTaskOrder;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public int getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(int idEmployee) {
    this.idEmployee = idEmployee;
  }

  public int getIdOrderTask() {
    return idOrderTask;
  }

  public void setIdOrderTask(int idOrderTask) {
    this.idOrderTask = idOrderTask;
  }

}
