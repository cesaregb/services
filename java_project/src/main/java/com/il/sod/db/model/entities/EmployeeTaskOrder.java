package com.il.sod.db.model.entities;

import javax.persistence.*;


/**
 * The persistent class for the EmployeeTaskOrder database table.
 */
@Entity
@NamedQuery(name = "EmployeeTaskOrder.findAll", query = "SELECT e FROM EmployeeTaskOrder e")
public class EmployeeTaskOrder implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idEmployeeTaskOrder;

  private String comments;

  //bi-directional many-to-one association to Employee
  @ManyToOne
  @JoinColumn(name = "idEmployee")
  private Employee employee;

  //bi-directional many-to-one association to OrderTask
  @ManyToOne
  @JoinColumn(name = "idOrderTask")
  private OrderTask orderTask;

  public EmployeeTaskOrder() {
  }

  public int getIdEmployeeTaskOrder() {
    return this.idEmployeeTaskOrder;
  }

  public void setIdEmployeeTaskOrder(int idEmployeeTaskOrder) {
    this.idEmployeeTaskOrder = idEmployeeTaskOrder;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public OrderTask getOrderTask() {
    return this.orderTask;
  }

  public void setOrderTask(OrderTask orderTask) {
    this.orderTask = orderTask;
  }

  @Override
  public Integer getId() {
    return this.idEmployeeTaskOrder;
  }

  @Override
  public EmployeeTaskOrder setId(Integer id) {
    this.idEmployeeTaskOrder = id;
    return this;
  }
}
