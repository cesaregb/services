package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the PromotionDTO database table.
 * 
 */
@Entity
@NamedQuery(name="Promotion.findAll", query="SELECT p FROM PriceAdjustment p")
public class PriceAdjustment extends SoftDeleteEntity implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPromotion;

	private double amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLimit;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private int maxUses;

	private int minimumAmount;

	private String name;

	private int orderLimit;

	private String promoCode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to OrderPromotion
	@OneToMany(mappedBy="promotion")
	private Set<PriceAdjustmentPromotion> orderPromotions;

	//bi-directional many-to-one association to PromotionTypeDTO
	@ManyToOne
	@JoinColumn(name="idPromotionType")
	private PriceAdjustmentType promotionType;

	private int discountType;

	public PriceAdjustment() {
	}

	public int getIdPromotion() {
		return this.idPromotion;
	}

	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateLimit() {
		return this.dateLimit;
	}

	public void setDateLimit(Date dateLimit) {
		this.dateLimit = dateLimit;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getMaxUses() {
		return this.maxUses;
	}

	public void setMaxUses(int maxUses) {
		this.maxUses = maxUses;
	}

	public int getMinimumAmount() {
		return this.minimumAmount;
	}

	public void setMinimumAmount(int minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderLimit() {
		return this.orderLimit;
	}

	public void setOrderLimit(int orderLimit) {
		this.orderLimit = orderLimit;
	}

	public String getPromoCode() {
		return this.promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Set<PriceAdjustmentPromotion> getOrderPromotions() {
		return this.orderPromotions;
	}

	public void setOrderPromotions(Set<PriceAdjustmentPromotion> orderPromotions) {
		this.orderPromotions = orderPromotions;
	}

	public PriceAdjustmentPromotion addOrderPromotion(PriceAdjustmentPromotion orderPromotion) {
		getOrderPromotions().add(orderPromotion);
		orderPromotion.setPromotion(this);

		return orderPromotion;
	}

	public PriceAdjustmentPromotion removeOrderPromotion(PriceAdjustmentPromotion orderPromotion) {
		getOrderPromotions().remove(orderPromotion);
		orderPromotion.setPromotion(null);

		return orderPromotion;
	}

	public PriceAdjustmentType getPromotionType() {
		return this.promotionType;
	}

	public void setPromotionType(PriceAdjustmentType promotionType) {
		this.promotionType = promotionType;
	}

	public int getDiscountType() {
		return discountType;
	}

	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PriceAdjustment)) return false;

		PriceAdjustment promotion = (PriceAdjustment) o;

		if (idPromotion != promotion.idPromotion) return false;
		if (Double.compare(promotion.amount, amount) != 0) return false;
		if (maxUses != promotion.maxUses) return false;
		if (minimumAmount != promotion.minimumAmount) return false;
		if (orderLimit != promotion.orderLimit) return false;
		if (discountType != promotion.discountType) return false;
		if (dateLimit != null ? !dateLimit.equals(promotion.dateLimit) : promotion.dateLimit != null) return false;
		if (description != null ? !description.equals(promotion.description) : promotion.description != null)
			return false;
		if (endDate != null ? !endDate.equals(promotion.endDate) : promotion.endDate != null) return false;
		if (name != null ? !name.equals(promotion.name) : promotion.name != null) return false;
		if (promoCode != null ? !promoCode.equals(promotion.promoCode) : promotion.promoCode != null) return false;
		return startDate != null ? startDate.equals(promotion.startDate) : promotion.startDate == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idPromotion;
		temp = Double.doubleToLongBits(amount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (dateLimit != null ? dateLimit.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
		result = 31 * result + maxUses;
		result = 31 * result + minimumAmount;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + orderLimit;
		result = 31 * result + (promoCode != null ? promoCode.hashCode() : 0);
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + discountType;
		return result;
	}

	@Override
	public Integer getId() {
		return this.idPromotion;
	}

	@Override
	public PriceAdjustment setId(Integer id) {
		this.idPromotion = id;
		return this;
	}



}