package com.il.sod.rest.dto.db;

public class OrderPriceAdjustmentDTO {
	private int idOrderPriceAdjustment;
	private double cantidad;
	private int idOrder;
	private int idPriceAdjustment;

	public int getIdOrderPriceAdjustment() {
		return idOrderPriceAdjustment;
	}

	public void setIdOrderPriceAdjustment(int idOrderPriceAdjustment) {
		this.idOrderPriceAdjustment = idOrderPriceAdjustment;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public Integer getIdPriceAdjustment() {
		return idPriceAdjustment;
	}

	public void setIdPriceAdjustment(Integer idPriceAdjustment) {
		this.idPriceAdjustment = idPriceAdjustment;
	}
}