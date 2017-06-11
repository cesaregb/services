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
	private double amount;
	private double pending;
	private String cupons;

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPending() {
		return pending;
	}

	public void setPending(double pending) {
		this.pending = pending;
	}

	public String getCupons() {
		return cupons;
	}

	public void setCupons(String cupons) {
		this.cupons = cupons;
	}

	public List<Integer> getOrders() {
		return orders;
	}

	public void setOrders(List<Integer> orders) {
		this.orders = orders;
	}
}
