package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.Set;

public class OrderDTO {
	private int idOrder;
	private String comments;
	private int idAddressDeliver;
	private int idAddressPickup;
	private double price;
	private int status;
	private ClientDTO client;
	private int idOrderType;
	private Set<OrderTaskDTO> orderTasks;
	private Set<OrderPickNDeliverDTO> orderPickNdelivers;
	private Set<PaymentInfoDTO> paymentInfos;
	private Date created;
	private Date updated;
	private int time;
	private Date pickUpDate;
	private Date deliverDate;
	private Set<ServiceDTO> services;
	
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
	public Set<OrderTaskDTO> getOrderTasks() {
		return orderTasks;
	}
	public void setOrderTasks(Set<OrderTaskDTO> orderTasks) {
		this.orderTasks = orderTasks;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Set<OrderPickNDeliverDTO> getOrderPickNdelivers() {
		return orderPickNdelivers;
	}
	public void setOrderPickNdelivers(Set<OrderPickNDeliverDTO> orderPickNdelivers) {
		this.orderPickNdelivers = orderPickNdelivers;
	}
	public Set<PaymentInfoDTO> getPaymentInfos() {
		return paymentInfos;
	}
	public void setPaymentInfos(Set<PaymentInfoDTO> paymentInfos) {
		this.paymentInfos = paymentInfos;
	}
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public int getIdOrderType() {
		return idOrderType;
	}
	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}
	public Set<ServiceDTO> getServices() {
		return services;
	}
	public void setServices(Set<ServiceDTO> services) {
		this.services = services;
	}
}
