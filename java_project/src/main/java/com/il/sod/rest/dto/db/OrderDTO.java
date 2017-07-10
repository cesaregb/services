package com.il.sod.rest.dto.db;

import lombok.Data;

import java.util.Date;
import java.util.Set;

//@Accessors(fluent = true)
@Data
public class OrderDTO extends DeletableDTO {
  private Integer idOrder;
  private String comments;
  private Integer idAddressDeliver;
  private Integer idAddressPickup;
  private Date pickUpDate;
  private Date deliverDate;
  private Integer status;
  private ClientDTO client;
  private Integer idOrderType;
  private Set<OrderTaskDTO> orderTasks;
  private PaymentInfoDTO paymentInfo;
  private Date created;
  private Date updated;
  private Integer time;
  private Set<ServiceDTO> services;
  private Integer createdBy;

  // flat information.
  private String clientName;
  private String orderTypeName;
  private Double completed;

  private Boolean paymentStatus;
  private Set<OrderPriceAdjustmentDTO> orderPriceAdjustments;
  private Integer idCashOut;

  private Double totalServices;
  private Double total;
  private Double pickUpPrice;
  private Double deliverPrice;
  private Double discount;

  public Double getSubtotal(){
    return this.totalServices + this.pickUpPrice + this.deliverPrice;
  }

}
