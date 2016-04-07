package com.il.sod.db.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the OrderPickNDeliver database table.
 * 
 */
@Entity
@NamedQuery(name="OrderPickNDeliver.findAll", query="SELECT o FROM OrderPickNDeliver o")
public class OrderPickNDeliver implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderPickNDeliver;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private int typeAction;

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idAddress")
	private Address address;

	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idOrder")
	private Order order;

	public OrderPickNDeliver() {
	}

	public int getIdOrderPickNDeliver() {
		return this.idOrderPickNDeliver;
	}

	public void setIdOrderPickNDeliver(int idOrderPickNDeliver) {
		this.idOrderPickNDeliver = idOrderPickNDeliver;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getTypeAction() {
		return this.typeAction;
	}

	public void setTypeAction(int typeAction) {
		this.typeAction = typeAction;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public Integer getId() {
		return this.idOrderPickNDeliver;
	}

	@Override
	public OrderPickNDeliver setId(Integer id) {
		this.idOrderPickNDeliver = id;
		return this;
	}

}