package com.il.sod.db.model.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cesaregb on 6/3/17.
 */
@Entity
public class CashOut extends SoftDeleteEntity implements IEntity<Integer>  {
	private int idCashOut;
	private Timestamp created;
	private Integer user;
	private Double amount;
	private Double pending;
	private Double coupons;

	@Id
	@Column(name = "idCashOut")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdCashOut() {
		return idCashOut;
	}

	public void setIdCashOut(int idCashOut) {
		this.idCashOut = idCashOut;
	}

	@Basic
	@Column(name = "created")
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	@Basic
	@Column(name = "user")
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
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
	@Column(name = "pending")
	public Double getPending() {
		return pending;
	}

	public void setPending(Double pending) {
		this.pending = pending;
	}

	@Basic
	@Column(name = "coupons")
	public Double getCoupons() {
		return coupons;
	}

	public void setCoupons(Double coupons) {
		this.coupons = coupons;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CashOut cashOut = (CashOut) o;
		return idCashOut == cashOut.idCashOut &&
				Objects.equal(created, cashOut.created) &&
				Objects.equal(user, cashOut.user) &&
				Objects.equal(amount, cashOut.amount) &&
				Objects.equal(pending, cashOut.pending) &&
				Objects.equal(coupons, cashOut.coupons);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idCashOut, created, user, amount, pending, coupons);
	}

	@Override
	@Transient
	public Integer getId() {
		return this.idCashOut;
	}

	@Override
	public IEntity setId(Integer id) {
		this.idCashOut = id;
		return this;
	}
}
