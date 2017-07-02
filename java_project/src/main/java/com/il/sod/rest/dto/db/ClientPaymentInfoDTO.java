package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.il.sod.rest.dto.GenericDBDTO;

public class ClientPaymentInfoDTO implements GenericDBDTO {

  private int idClientPaymentInfo;
  private String token;
  private int type;
  private int idClient;
  private boolean prefered;

  public ClientPaymentInfoDTO() {
  }

  public int getIdClientPaymentInfo() {
    return this.idClientPaymentInfo;
  }

  public void setIdClientPaymentInfo(int idClientPaymentInfo) {
    this.idClientPaymentInfo = idClientPaymentInfo;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getIdClient() {
    return idClient;
  }

  public void setIdClient(int idClient) {
    this.idClient = idClient;
  }

  public boolean isPrefered() {
    return prefered;
  }

  public void setPrefered(boolean prefered) {
    this.prefered = prefered;
  }

  @Override
  @JsonIgnore
  public Integer getParentId() {
    return this.idClient;
  }
}