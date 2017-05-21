package com.il.sod.db.model.entities;

import javax.persistence.*;


/**
 * The persistent class for the OrderPriceAdjustment database table.
 * 
 */
@Entity
@NamedQuery(name="OrderPriceAdjustment.findAll", query="SELECT o FROM PriceAdjustment o")
public class PriceAdjustment extends SoftDeleteEntity  implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderPriceAdjustment;

	private double cantidad;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order order;

	//bi-directional many-to-one association to PriceAdjustmentDTO
	@ManyToOne
	@JoinColumn(name="idPriceAdjustment")
	private PriceAdjustmentType priceAdjustmentType;

	public PriceAdjustment() {
	}

	public int getIdOrderPriceAdjustment() {
		return this.idOrderPriceAdjustment;
	}

	public void setIdOrderPriceAdjustment(int idOrderPriceAdjustment) {
		this.idOrderPriceAdjustment = idOrderPriceAdjustment;
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

	public PriceAdjustmentType getPriceAdjustmentType() {
		return this.priceAdjustmentType;
	}

	public void setPriceAdjustmentType(PriceAdjustmentType priceAdjustment) {
		this.priceAdjustmentType = priceAdjustment;
	}

	@Override
	public Integer getId() {
		return this.idOrderPriceAdjustment;
	}

	@Override
	public PriceAdjustment setId(Integer id) {
		this.idOrderPriceAdjustment = id;
		return this;
	}
}