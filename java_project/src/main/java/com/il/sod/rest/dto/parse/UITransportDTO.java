package com.il.sod.rest.dto.parse;

import java.util.Date;

/**
 * Created by cesaregb on 1/15/17.
 */
public class UITransportDTO {
  private int idAddress;
  private int type;
  private Date date;
  private double price;

  public UITransportDTO() {
  }

  public UITransportDTO(int idAddress, Date date, int type, double price) {
    this.idAddress = idAddress;
    this.type = type;
    this.date = date;
    this.price = price;
  }

  public int getIdAddress() {
    return idAddress;
  }

  public void setIdAddress(int idAddress) {
    this.idAddress = idAddress;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
