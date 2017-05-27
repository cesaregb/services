package com.il.sod.db.model.entities;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * Created by cesaregb on 5/21/17.
 */
@Entity
public class OrderPriceAdjustment {
	private int idOrderPriceAdjustment;
	private double cantidad;
	private Order order;
	private PriceAdjustment priceAdjustment;

	@Id
	@Column(name = "idOrderPriceAdjustment")
	public int getIdOrderPriceAdjustment() {
		return idOrderPriceAdjustment;
	}

	public void setIdOrderPriceAdjustment(int idOrderPriceAdjustment) {
		this.idOrderPriceAdjustment = idOrderPriceAdjustment;
	}

	@Basic
	@Column(name = "cantidad")
	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderPriceAdjustment that = (OrderPriceAdjustment) o;
		return idOrderPriceAdjustment == that.idOrderPriceAdjustment &&
				Double.compare(that.cantidad, cantidad) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idOrderPriceAdjustment, cantidad);
	}

	@ManyToOne
	@JoinColumn(name = "idOrder", referencedColumnName = "idOrder", nullable = false)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order orders) {
		this.order = orders;
	}

	@ManyToOne
	@JoinColumn(name = "idPriceAdjustment", referencedColumnName = "idPriceAdjustment", nullable = false)
	public PriceAdjustment getPriceAdjustment() {
		return priceAdjustment;
	}

	public void setPriceAdjustment(PriceAdjustment priceAdjustment) {
		this.priceAdjustment = priceAdjustment;
	}

}
