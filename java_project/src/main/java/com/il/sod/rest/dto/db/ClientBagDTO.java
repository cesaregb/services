package com.il.sod.rest.dto.db;

public class ClientBagDTO {

  private int idClientBags;
  private boolean inOrder;
  private String number;
  private int idBagtype;
  private int idClient;

  public int getIdClientBags() {
    return idClientBags;
  }

  public void setIdClientBags(int idClientBags) {
    this.idClientBags = idClientBags;
  }

  public boolean isInOrder() {
    return inOrder;
  }

  public void setInOrder(boolean inOrder) {
    this.inOrder = inOrder;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public int getIdBagtype() {
    return idBagtype;
  }

  public void setIdBagtype(int idBagtype) {
    this.idBagtype = idBagtype;
  }

  public int getIdClient() {
    return idClient;
  }

  public void setIdClient(int idClient) {
    this.idClient = idClient;
  }
}