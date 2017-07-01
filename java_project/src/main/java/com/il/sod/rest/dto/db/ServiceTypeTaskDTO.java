package com.il.sod.rest.dto.db;

public class ServiceTypeTaskDTO {
  private int idServiceTypeTask;
  private int idServiceType;
  private String serviceTypeName;
  private TaskDTO task;
  //	private int idTask;
  private int sortingOrder;
  private int time;
  private String taskTypeName;

  public ServiceTypeTaskDTO() {
  }

  public ServiceTypeTaskDTO(TaskDTO task) {
    this.task = task;
  }

  public int getIdServiceTypeTask() {
    return idServiceTypeTask;
  }

  public void setIdServiceTypeTask(int idServiceTypeTask) {
    this.idServiceTypeTask = idServiceTypeTask;
  }

  public int getSortingOrder() {
    return sortingOrder;
  }

  public void setSortingOrder(int sortingOrder) {
    this.sortingOrder = sortingOrder;
  }

  public int getIdServiceType() {
    return idServiceType;
  }

  public void setIdServiceType(int idServiceType) {
    this.idServiceType = idServiceType;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public TaskDTO getTask() {
    return task;
  }

  public void setTask(TaskDTO task) {
    this.task = task;
  }

  public String getServiceTypeName() {
    return serviceTypeName;
  }

  public void setServiceTypeName(String serviceTypeName) {
    this.serviceTypeName = serviceTypeName;
  }

  public String getTaskTypeName() {
    return taskTypeName;
  }

  public void setTaskTypeName(String taskTypeName) {
    this.taskTypeName = taskTypeName;
  }
}
