package com.il.sod.rest.dto.serve;

public class WPaymentInfoDTO {
  private String transactionInfo;
  private int type;

  public WPaymentInfoDTO() {
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
}