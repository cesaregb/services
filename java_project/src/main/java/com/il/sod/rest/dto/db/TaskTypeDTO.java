package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Set;

@JsonInclude(Include.NON_NULL)
public class TaskTypeDTO extends DeletableDTO {
  private int idTaskType;
  private String description;
  private String name;
  private Set<TaskDTO> tasks;
  private boolean ordersOnly;

  public int getIdTaskType() {
    return idTaskType;
  }

  public void setIdTaskType(int idTaskType) {
    this.idTaskType = idTaskType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<TaskDTO> getTasks() {
    return tasks;
  }

  public void setTasks(Set<TaskDTO> tasks) {
    this.tasks = tasks;
  }

  public boolean isOrdersOnly() {
    return ordersOnly;
  }

  public void setOrdersOnly(boolean ordersOnly) {
    this.ordersOnly = ordersOnly;
  }
}
