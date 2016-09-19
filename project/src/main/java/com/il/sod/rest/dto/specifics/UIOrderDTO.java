package com.il.sod.rest.dto.specifics;

import java.util.Date;
import java.util.List;

public class UIOrderDTO {
	private int idOrder;
	private int idClient;
	private int idAddressPickup;
	private int idAddressDeliver;
	private double totalServices;
	private double total;
	private String comments;
	private Date pickUpDate;
	private Date deliveryDate;
	private List<UIServiceDTO> services;
	private WPaymentInfoDTO paymentInfo; 
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdAddressPickup() {
		return idAddressPickup;
	}
	public void setIdAddressPickup(int idAddressPickup) {
		this.idAddressPickup = idAddressPickup;
	}
	public int getIdAddressDeliver() {
		return idAddressDeliver;
	}
	public void setIdAddressDeliver(int idAddressDeliver) {
		this.idAddressDeliver = idAddressDeliver;
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

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public List<UIServiceDTO> getServices() {
		return services;
	}
	public void setServices(List<UIServiceDTO> services) {
		this.services = services;
	}
	public WPaymentInfoDTO getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(WPaymentInfoDTO paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
}
