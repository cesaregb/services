package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.il.sod.rest.dto.GenericDBDTO;

public class PaymentInfoDTO implements GenericDBDTO {

  private int idPaymentInfo;
  private String transactionInfo;
  private int type;
  private int idOrder;

  public PaymentInfoDTO() {
  }

  public int getIdPaymentInfo() {
    return this.idPaymentInfo;
  }

  public void setIdPaymentInfo(int idPaymentInfo) {
    this.idPaymentInfo = idPaymentInfo;
  }

  public String getTransactionInfo() {
    return this.transactionInfo;
  }

  public void setTransactionInfo(String transactionInfo) {
    this.transactionInfo = transactionInfo;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getIdOrder() {
    return idOrder;
  }

  public void setIdOrder(int idOrder) {
    this.idOrder = idOrder;
  }

  @Override
  @JsonIgnore
  public Integer getParentId() {
    return this.idOrder;
  }

}