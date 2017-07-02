package com.il.sod.rest.dto.db;

import lombok.Data;

@Data
public class ProductDTO extends DeletableDTO {
  private int idProduct;
  private int maxQty;
  private String name;
  private double price;
  private int idProductType;
  private String typeName;
  private int quantity;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProductDTO)) return false;

    ProductDTO that = (ProductDTO) o;

    if (idProduct != that.idProduct) return false;
    if (maxQty != that.maxQty) return false;
    if (Double.compare(that.price, price) != 0) return false;
    if (idProductType != that.idProductType) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = idProduct;
    result = 31 * result + maxQty;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + idProductType;
    result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
    return result;
  }
}