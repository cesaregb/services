package com.il.sod.rest.dto.db;

import java.util.Date;


public class OrderPickNDeliverDTO {

	private int idOrderPickNDeliver;

	private String comments;

	private Date time;

	private int typeAction;

	private int address;

	private int order;

	public OrderPickNDeliverDTO() {
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

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}