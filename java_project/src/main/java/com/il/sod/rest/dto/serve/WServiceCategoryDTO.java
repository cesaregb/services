package com.il.sod.rest.dto.serve;

import lombok.Data;

import java.util.Set;


@Data
public class WServiceCategoryDTO {
  private int idServiceCategory;
  private String description;
  private String name;
  private Set<WServiceTypeDTO> serviceTypes;

  public WServiceCategoryDTO() {
  }

}