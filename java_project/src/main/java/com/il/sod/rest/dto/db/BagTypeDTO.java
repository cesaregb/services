package com.il.sod.rest.dto.db;

public class BagTypeDTO {

  private int idBagtype;
  private String name;
  private int size;

  public int getIdBagtype() {
    return idBagtype;
  }

  public void setIdBagtype(int idBagtype) {
    this.idBagtype = idBagtype;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}