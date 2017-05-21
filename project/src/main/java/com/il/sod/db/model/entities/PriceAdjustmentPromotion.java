package com.il.sod.db.model.entities;

import javax.persistence.*;


/**
 * The persistent class for the OrderPromotion database table.
 * 
 */
@Entity
@NamedQuery(name="OrderPromotion.findAll", query="SELECT o FROM PriceAdjustmentPromotion o")
public class PriceAdjustmentPromotion implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderPromotion;

	private double cantidad;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order order;

	//bi-directional many-to-one association to PromotionDTO
	@ManyToOne
	@JoinColumn(name="idPromotion")
	private PriceAdjustment promotion;

	public PriceAdjustmentPromotion() {
	}

	public int getIdOrderPromotion() {
		return this.idOrderPromotion;
	}

	public void setIdOrderPromotion(int idOrderPromotion) {
		this.idOrderPromotion = idOrderPromotion;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public PriceAdjustment getPromotion() {
		return this.promotion;
	}

	public void setPromotion(PriceAdjustment promotion) {
		this.promotion = promotion;
	}

	@Override
	public Integer getId() {
		return this.idOrderPromotion;
	}

	@Override
	public PriceAdjustmentPromotion setId(Integer id) {
		this.idOrderPromotion = id;
		return this;
	}
}