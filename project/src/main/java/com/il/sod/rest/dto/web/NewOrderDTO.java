package com.il.sod.rest.dto.web;

import java.util.Date;
import java.util.List;

public class NewOrderDTO {
	private int idClient;
	private int idAddressPickup;
	private int idAddressDeliver;
	private double price;
	private String comments;
	private Date pickUpDate;
	private String pickUpComments;
	private Date deliveryDate;
	private String deliveryComments;
	private List<InpServiceDTO> services;
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getPickUpComments() {
		return pickUpComments;
	}
	public void setPickUpComments(String pickUpComments) {
		this.pickUpComments = pickUpComments;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryComments() {
		return deliveryComments;
	}
	public void setDeliveryComments(String deliveryComments) {
		this.deliveryComments = deliveryComments;
	}
	public List<InpServiceDTO> getServices() {
		return services;
	}
	public void setServices(List<InpServiceDTO> services) {
		this.services = services;
	}
}
