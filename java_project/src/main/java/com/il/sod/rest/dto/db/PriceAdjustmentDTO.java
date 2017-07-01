package com.il.sod.rest.dto.db;

import java.sql.Timestamp;
import java.util.Set;

public class PriceAdjustmentDTO extends DeletableDTO {
  private int idPriceAdjustment;
  private String name;
  private String description;
  private Timestamp startDate;
  private Timestamp endDate;
  private Integer maxUses;
  private double amount;
  private String promoCode;
  private int orderLimit;
  private Timestamp dateLimit;
  private int minimumAmount;
  private int discountType;
  private Set<OrderPriceAdjustmentDTO> orderPriceAdjustments;
  private int idPriceAdjustmentType;


  public int getIdPriceAdjustment() {
    return idPriceAdjustment;
  }

  public void setIdPriceAdjustment(int idPriceAdjustment) {
    this.idPriceAdjustment = idPriceAdjustment;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }

  public Integer getMaxUses() {
    return maxUses;
  }

  public void setMaxUses(Integer maxUses) {
    this.maxUses = maxUses;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getPromoCode() {
    return promoCode;
  }

  public void setPromoCode(String promoCode) {
    this.promoCode = promoCode;
  }

  public int getOrderLimit() {
    return orderLimit;
  }

  public void setOrderLimit(int orderLimit) {
    this.orderLimit = orderLimit;
  }

  public Timestamp getDateLimit() {
    return dateLimit;
  }

  public void setDateLimit(Timestamp dateLimit) {
    this.dateLimit = dateLimit;
  }

  public int getMinimumAmount() {
    return minimumAmount;
  }

  public void setMinimumAmount(int minimumAmount) {
    this.minimumAmount = minimumAmount;
  }

  public int getDiscountType() {
    return discountType;
  }

  public void setDiscountType(int discountType) {
    this.discountType = discountType;
  }

  public Set<OrderPriceAdjustmentDTO> getOrderPriceAdjustments() {
    return orderPriceAdjustments;
  }

  public void setOrderPriceAdjustments(Set<OrderPriceAdjustmentDTO> orderPriceAdjustments) {
    this.orderPriceAdjustments = orderPriceAdjustments;
  }

  public int getIdPriceAdjustmentType() {
    return idPriceAdjustmentType;
  }

  public void setIdPriceAdjustmentType(int idPriceAdjustmentType) {
    this.idPriceAdjustmentType = idPriceAdjustmentType;
  }
}