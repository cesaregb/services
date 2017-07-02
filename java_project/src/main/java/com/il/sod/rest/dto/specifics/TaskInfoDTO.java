package com.il.sod.rest.dto.specifics;

import com.il.sod.rest.dto.db.TaskDTO;

import java.util.Date;

public class TaskInfoDTO {

  private TaskDTO task;
  private int status;
  private Date started;
  private Date ended;
  private int time;
  private int sortingOrder;
  private int typeTask = 0; // 0 = order; 1 = service
  private int idParent = 0; // idOrder or idService.

  public TaskDTO getTask() {
    return task;
  }

  public void setTask(TaskDTO task) {
    this.task = task;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Date getStarted() {
    return started;
  }

  public void setStarted(Date started) {
    this.started = started;
  }

  public Date getEnded() {
    return ended;
  }

  public void setEnded(Date ended) {
    this.ended = ended;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getSortingOrder() {
    return sortingOrder;
  }

  public void setSortingOrder(int sortingOrder) {
    this.sortingOrder = sortingOrder;
  }

  public int getTypeTask() {
    return typeTask;
  }

  public void setTypeTask(int typeTask) {
    this.typeTask = typeTask;
  }

  public int getIdParent() {
    return idParent;
  }

  public void setIdParent(int idParent) {
    this.idParent = idParent;
  }
}
