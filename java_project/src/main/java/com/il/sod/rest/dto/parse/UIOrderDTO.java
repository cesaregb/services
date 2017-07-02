package com.il.sod.rest.dto.parse;

import com.il.sod.rest.dto.serve.WPaymentInfoDTO;
import lombok.Data;

import java.util.List;

@Data
//@Accessors(fluent = true)
public class UIOrderDTO {
  private int idOrder;
  private int idOrderType;
  private int idClient;
  private double totalServices;
  private double total;
  private double discount;
  private boolean paymentStatus;
  private String comments;

  private List<UITransportDTO> transport;
  private List<UIServiceDTO> services;
  private WPaymentInfoDTO paymentInfo;

}
