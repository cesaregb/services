package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the PaymentInfo database table.
 * 
 */
@Entity
@NamedQuery(name="PaymentInfo.findAll", query="SELECT p FROM PaymentInfo p")
public class PaymentInfo implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPaymentInfo;

	private String transactionInfo;

	private int type;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order order;

	public PaymentInfo() {
	}

	public int getIdPaymentInfo() {
		return this.idPaymentInfo;
	}

	public void setIdPaymentInfo(int idPaymentInfo) {
		this.idPaymentInfo = idPaymentInfo;
	}

	public String getTransactionInfo() {
		return this.transactionInfo;
	}

	public void setTransactionInfo(String transactionInfo) {
		this.transactionInfo = transactionInfo;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public Integer getId() {
		return this.idPaymentInfo;
	}

	@Override
	public PaymentInfo setId(Integer id) {
		this.idPaymentInfo = id;
		return this;
	}

}