package com.il.sod.rest.dto.db;

import java.util.Date;


public class PriceAdjustmentDTO extends DeletableDTO{
	private int idPriceAdjustment;
	private double amount;
	private Date dateLimit;
	private String description;
	private Date endDate;
	private int maxUses;
	private int minimumAmount;
	private String name;
	private int orderLimit;
	private String promoCode;
	private Date startDate;
	private Integer idPriceAdjustmentType;
	private Integer discountType;

	public int getIdPriceAdjustment() {
		return idPriceAdjustment;
	}

	public void setIdPriceAdjustment(int idPriceAdjustment) {
		this.idPriceAdjustment = idPriceAdjustment;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateLimit() {
		return dateLimit;
	}

	public void setDateLimit(Date dateLimit) {
		this.dateLimit = dateLimit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getMaxUses() {
		return maxUses;
	}

	public void setMaxUses(int maxUses) {
		this.maxUses = maxUses;
	}

	public int getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(int minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(int orderLimit) {
		this.orderLimit = orderLimit;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getIdPriceAdjustmentType() {
		return idPriceAdjustmentType;
	}

	public void setIdPriceAdjustmentType(Integer idPriceAdjustmentType) {
		this.idPriceAdjustmentType = idPriceAdjustmentType;
	}

	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}
}