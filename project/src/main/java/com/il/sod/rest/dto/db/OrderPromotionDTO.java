package com.il.sod.rest.dto.db;

public class OrderPromotionDTO {
	private int idOrderPromotion;
	private double cantidad;
	private Integer idOrder;
	private Integer idPromotion;

	public int getIdOrderPromotion() {
		return idOrderPromotion;
	}

	public void setIdOrderPromotion(int idOrderPromotion) {
		this.idOrderPromotion = idOrderPromotion;
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

	public Integer getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}
}