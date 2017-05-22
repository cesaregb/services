package com.il.sod.db.model.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

/**
 * Created by cesaregb on 5/21/17.
 */
@Entity
public class PriceAdjustment extends SoftDeleteEntity implements IEntity<Integer>{
	private int idPriceAdjustment;
	private String name;
	private String description;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer maxUses;
	private Double amount;
	private String promoCode;
	private Integer orderLimit;
	private Timestamp dateLimit;
	private Integer minimumAmount;
	private Integer discountType;
	private Set<OrderPriceAdjustment> orderPriceAdjustments;
	private PriceAdjustmentType priceAdjustmentType;

	@Id
	@Column(name = "idPriceAdjustment")
	public int getIdPriceAdjustment() {
		return idPriceAdjustment;
	}

	public void setIdPriceAdjustment(int idPriceAdjustment) {
		this.idPriceAdjustment = idPriceAdjustment;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "startDate")
	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@Basic
	@Column(name = "endDate")
	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@Basic
	@Column(name = "maxUses")
	public Integer getMaxUses() {
		return maxUses;
	}

	public void setMaxUses(Integer maxUses) {
		this.maxUses = maxUses;
	}

	@Basic
	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Basic
	@Column(name = "promoCode")
	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	@Basic
	@Column(name = "orderLimit")
	public Integer getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	@Basic
	@Column(name = "dateLimit")
	public Timestamp getDateLimit() {
		return dateLimit;
	}

	public void setDateLimit(Timestamp dateLimit) {
		this.dateLimit = dateLimit;
	}

	@Basic
	@Column(name = "minimumAmount")
	public Integer getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(Integer minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	@Basic
	@Column(name = "discountType")
	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PriceAdjustment that = (PriceAdjustment) o;
		return idPriceAdjustment == that.idPriceAdjustment &&
				Objects.equal(name, that.name) &&
				Objects.equal(description, that.description) &&
				Objects.equal(startDate, that.startDate) &&
				Objects.equal(endDate, that.endDate) &&
				Objects.equal(maxUses, that.maxUses) &&
				Objects.equal(amount, that.amount) &&
				Objects.equal(promoCode, that.promoCode) &&
				Objects.equal(orderLimit, that.orderLimit) &&
				Objects.equal(dateLimit, that.dateLimit) &&
				Objects.equal(minimumAmount, that.minimumAmount) &&
				Objects.equal(discountType, that.discountType);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idPriceAdjustment, name, description, startDate, endDate, maxUses, amount, promoCode, orderLimit, dateLimit, minimumAmount, discountType);
	}

	@OneToMany(mappedBy = "priceAdjustment")
	public Set<OrderPriceAdjustment> getOrderPriceAdjustments() {
		return orderPriceAdjustments;
	}

	public void setOrderPriceAdjustments(Set<OrderPriceAdjustment> orderPriceAdjustments) {
		this.orderPriceAdjustments = orderPriceAdjustments;
	}

	@ManyToOne
	@JoinColumn(name = "idPriceAdjustmentType", referencedColumnName = "idPriceAdjustmentType", nullable = false)
	public PriceAdjustmentType getPriceAdjustmentType() {
		return priceAdjustmentType;
	}

	public void setPriceAdjustmentType(PriceAdjustmentType priceAdjustmentType) {
		this.priceAdjustmentType = priceAdjustmentType;
	}

	@Override
	public Integer getId() {
		return this.idPriceAdjustment;
	}

	@Override
	public IEntity setId(Integer id) {
		this.idPriceAdjustment =  id;
		return this;
	}

}
