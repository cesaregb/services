package com.il.sod.rest.dto.db;

public class DistanceInfoDTO {
  private int idDistanceInfo;
  private int distance;
  private double price;
  private int source;
  private int idStore;

  public int getIdDistanceInfo() {
    return idDistanceInfo;
  }

  public void setIdDistanceInfo(int idDistanceInfo) {
    this.idDistanceInfo = idDistanceInfo;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getSource() {
    return source;
  }

  public void setSource(int source) {
    this.source = source;
  }

  public int getIdStore() {
    return idStore;
  }

  public void setIdStore(int idStore) {
    this.idStore = idStore;
  }
}