package com.il.sod.rest.dto.db;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cesaregb on 6/3/17.
 */
public class CashOutDTO  extends DeletableDTO{
	private int idCashOut;
	private Timestamp created;
	private int user;
	private double subtotal;
	private double pending;
	private double total;
	private double discount;

	private List<Integer> orders;

	public int getIdCashOut() {
		return idCashOut;
	}

	public void setIdCashOut(int idCashOut) {
		this.idCashOut = idCashOut;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}


	public double getPending() {
		return pending;
	}

	public void setPending(double pending) {
		this.pending = pending;
	}

	public List<Integer> getOrders() {
		return orders;
	}

	public void setOrders(List<Integer> orders) {
		this.orders = orders;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
