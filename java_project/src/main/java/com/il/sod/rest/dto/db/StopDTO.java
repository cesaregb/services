package com.il.sod.rest.dto.db;

public class StopDTO {

  private int idStops;
  private String arriveAt;
  private String description;
  private String name;
  private int time;
  private int idRoutes;
  private int type;
  private int idAddress;

  private AddressGenericDTO address;

  public int getIdStops() {
    return idStops;
  }

  public void setIdStops(int idStops) {
    this.idStops = idStops;
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

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getIdRoutes() {
    return idRoutes;
  }

  public void setIdRoutes(int idRoutes) {
    this.idRoutes = idRoutes;
  }

  public String getArriveAt() {
    return arriveAt;
  }

  public void setArriveAt(String arriveAt) {
    this.arriveAt = arriveAt;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public AddressGenericDTO getAddress() {
    return address;
  }

  public void setAddress(AddressGenericDTO address) {
    this.address = address;
  }

  public int getIdAddress() {
    return idAddress;
  }

  public void setIdAddress(int idAddress) {
    this.idAddress = idAddress;
  }

}