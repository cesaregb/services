package com.il.sod.rest.dto.db;

public class SpecsValueDTO {

  private int idSpecsValues;
  private int idSupplyType;
  private int type;
  private String value;
  private int idSpecs;
  private double serviceIncrement;
  private int prefered;
  private double specPrice;
  private int costType;
  private String specName;
  private String supplyName;

  public SpecsValueDTO() {
  }

  public int getIdSpecsValues() {
    return this.idSpecsValues;
  }

  public void setIdSpecsValues(int idSpecsValues) {
    this.idSpecsValues = idSpecsValues;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getIdSupplyType() {
    return idSupplyType;
  }

  public void setIdSupplyType(int idSupplyType) {
    this.idSupplyType = idSupplyType;
  }

  public double getServiceIncrement() {
    return serviceIncrement;
  }

  public void setServiceIncrement(double serviceIncrement) {
    this.serviceIncrement = serviceIncrement;
  }

  public int getIdSpecs() {
    return idSpecs;
  }

  public void setIdSpecs(int idSpecs) {
    this.idSpecs = idSpecs;
  }

  public int getPrefered() {
    return prefered;
  }

  public void setPrefered(int prefered) {
    this.prefered = prefered;
  }

  public double getSpecPrice() {
    return specPrice;
  }

  public void setSpecPrice(double specPrice) {
    this.specPrice = specPrice;
  }

  public int getCostType() {
    return costType;
  }

  public void setCostType(int costType) {
    this.costType = costType;
  }

  public String getSpecName() {
    return specName;
  }

  public void setSpecName(String specName) {
    this.specName = specName;
  }

  public String getSupplyName() {
    return supplyName;
  }

  public void setSupplyName(String supplyName) {
    this.supplyName = supplyName;
  }

}