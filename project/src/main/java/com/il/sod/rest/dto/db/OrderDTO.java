package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.Set;

public class OrderDTO {
	private int idOrder;
	private String comments;
	private int idAddressDeliver;
	private int idAddressPickup;
	private Date pickUpDate;
	private Date deliverDate;
	private double totalServices;
	private double total;
	private int status;
	private ClientDTO client;
	private int idOrderType;
	private Set<OrderTaskDTO> orderTasks;
	private PaymentInfoDTO paymentInfo;
	private Date created;
	private Date updated;
	private int time;
	private Set<ServiceDTO> services;
	private int createdBy;
	private int deleted;
	
	// flat information.
	private String clientName;
	private String orderTypeName;
	private double completed; 
	
	
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

	public double getTotalServices() {
		return totalServices;
	}

	public void setTotalServices(double totalServices) {
		this.totalServices = totalServices;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}
	public double getCompleted() {
		return completed;
	}
	public void setCompleted(double completed) {
		this.completed = completed;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public PaymentInfoDTO getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(PaymentInfoDTO paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
}
