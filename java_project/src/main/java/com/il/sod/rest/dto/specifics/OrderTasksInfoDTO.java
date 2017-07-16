package com.il.sod.rest.dto.specifics;

import com.il.sod.rest.dto.db.OrderTaskDTO;
import lombok.Data;

import java.util.Set;

@Data
public class OrderTasksInfoDTO {
  private int idOrder;
  private int idClient;
  private Boolean paymentStatus;
  private String clientName;
  private String orderTypeName;
  private double completed;
  private Set<OrderTaskDTO> orderTasks;
  private Set<ServiceTasksInfoDTO> services;
}
