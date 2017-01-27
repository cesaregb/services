package com.il.sod.rest.dto.parse;

import com.il.sod.rest.dto.serve.WPaymentInfoDTO;

import java.util.List;

public class UIOrderDTO {
	private int idOrder;
	private int idOrderType;
	private int idClient;
	private double totalServices;
	private double total;
	private String comments;

	private List<UITransportDTO> transport;
	private List<UIServiceDTO> services;
	private WPaymentInfoDTO paymentInfo;
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
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
	public int getIdOrderType() {
		return idOrderType;
	}
	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}

	public List<UITransportDTO> getTransport() {
		return transport;
	}

	public void setTransport(List<UITransportDTO> transport) {
		this.transport = transport;
	}
}
