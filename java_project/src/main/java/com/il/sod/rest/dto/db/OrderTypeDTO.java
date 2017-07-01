package com.il.sod.rest.dto.db;

import com.google.common.base.MoreObjects;

import java.util.List;

public class OrderTypeDTO extends DeletableDTO {
  private int idOrderType;
  private String description;
  private String name;
  private List<OrderTypeTaskDTO> orderTypeTask;
  private Integer transportInfo;

  public int getIdOrderType() {
    return idOrderType;
  }

  public void setIdOrderType(int idOrderType) {
    this.idOrderType = idOrderType;
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

  public List<OrderTypeTaskDTO> getOrderTypeTask() {
    return orderTypeTask;
  }

  public void setOrderTypeTask(List<OrderTypeTaskDTO> OrderTypeTask) {
    this.orderTypeTask = OrderTypeTask;
  }

  public Integer getTransportInfo() {
    return transportInfo;
  }

  public void setTransportInfo(Integer transportInfo) {
    this.transportInfo = transportInfo;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("idOrderType", idOrderType)
            .add("description", description)
            .add("name", name)
            .add("orderTypeTask", orderTypeTask)
            .add("transportInfo", transportInfo)
            .toString();
  }
}

