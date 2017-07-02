package com.il.sod.rest.dto.db;

public class ServiceSpecDTO {
  private int idServiceSpecs;
  private String comments;
  private Integer idService;
  private SpecDTO spec;
  private String selectedValue;
  private float specPrice;
  private float serviceIncrement;
  private int quantity;

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public SpecDTO getSpec() {
    return spec;
  }

  public void setSpec(SpecDTO spec) {
    this.spec = spec;
  }

  public String getSelectedValue() {
    return selectedValue;
  }

  public void setSelectedValue(String selectedValue) {
    this.selectedValue = selectedValue;
  }

  public float getSpecPrice() {
    return specPrice;
  }

  public void setSpecPrice(float specPrice) {
    this.specPrice = specPrice;
  }

  public float getServiceIncrement() {
    return serviceIncrement;
  }

  public void setServiceIncrement(float serviceIncrement) {
    this.serviceIncrement = serviceIncrement;
  }

  public int getIdServiceSpecs() {
    return idServiceSpecs;
  }

  public void setIdServiceSpecs(int idServiceSpecs) {
    this.idServiceSpecs = idServiceSpecs;
  }

  public Integer getIdService() {
    return idService;
  }

  public void setIdService(Integer idService) {
    this.idService = idService;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
