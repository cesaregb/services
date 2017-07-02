package com.il.sod.rest.dto.db;

public abstract class DeletableDTO {

  private Integer deleted;

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
}