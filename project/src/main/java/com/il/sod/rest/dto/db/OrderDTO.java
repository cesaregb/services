package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.List;

public class OrderDTO {
	private int idOrder;
	private String comments;
	private Date date;
	private int idAddressDeliver;
	private int idAddressPickup;
	private double price;
	private int status;
	private ClientDTO client;
	private Integer orderType;
	private List<OrderTaskDTO> orderTasks;
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getIdAddressDeliver() {
		return idAddressDeliver;
	}
	public void setIdAddressDeliver(int idAddressDeliver) {
		this.idAddressDeliver = idAddressDeliver;
	}
	public int getIdAddressPickup() {
		return idAddressPickup;
	}
	public void setIdAddressPickup(int idAddressPickup) {
		this.idAddressPickup = idAddressPickup;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
	public List<OrderTaskDTO> getOrderTasks() {
		return orderTasks;
	}
	public void setOrderTasks(List<OrderTaskDTO> orderTasks) {
		this.orderTasks = orderTasks;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

}
