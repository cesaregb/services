package com.il.sod.rest.dto.db;

import com.google.common.base.MoreObjects;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ServiceTypeDTO {
  private int idServiceType;
  private String description;
  private String name;
  private double price;
  private int time;
  private Set<Integer> services;
  private Set<SpecDTO> specs;
  private List<ServiceTypeTaskDTO> serviceTypeTasks;
  private int idServiceCategory;
  private String serviceTypeCategoryName;
  private Set<ProductTypeDTO> productTypes;
  private boolean calculator;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("idServiceType", idServiceType)
            .add("description", description)
            .add("name", name)
            .add("price", price)
            .add("time", time)
            .add("services", services)
            .add("idServiceCategory", idServiceCategory)
            .add("serviceTypeCategoryName", serviceTypeCategoryName)
            .add("calculator", calculator)
            .toString();
  }
}
