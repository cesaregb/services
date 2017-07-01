package com.il.sod.rest.dto.specifics;

import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.rest.dto.SimpleIdName;
import com.il.sod.rest.dto.db.AddressDTO;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.db.TaskDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class OrderReport {

  private int idOrder;
  private Date created;
  private double price;
  private int status;
  private SimpleIdName client;
  private SimpleIdName orderType;
  private String comments;
  private AddressDTO addressDeliver;
  private AddressDTO addressPickup;
  private Date pickUpDate;
  private Date deliverDate;
  private List<TaskDTO> tasks;
  private Set<ServiceDTO> services;
  private PaymentInfo paymentInfo;


}
