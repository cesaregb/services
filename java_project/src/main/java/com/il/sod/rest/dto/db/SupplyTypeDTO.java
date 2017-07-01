package com.il.sod.rest.dto.db;

import java.util.Set;

public class SupplyTypeDTO extends DeletableDTO {
  private int idSupplyType;
  private String description;
  private String name;
  private Set<SpecDTO> specs;
  private Set<SupplyDTO> supplies;

  public int getIdSupplyType() {
    return idSupplyType;
  }

  public void setIdSupplyType(int idSupplyType) {
    this.idSupplyType = idSupplyType;
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

  public Set<SupplyDTO> getSupplies() {
    return supplies;
  }

  public void setSupplies(Set<SupplyDTO> supplies) {
    this.supplies = supplies;
  }

  public Set<SpecDTO> getSpecs() {
    return specs;
  }

  public void setSpecs(Set<SpecDTO> specs) {
    this.specs = specs;
  }

}
