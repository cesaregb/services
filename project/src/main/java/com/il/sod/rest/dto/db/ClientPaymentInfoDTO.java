package com.il.sod.rest.dto.db;

public class ClientPaymentInfoDTO {

	private int idClientPaymentInfo;
	private String token;
	private int type;
	private int idClient;

	public ClientPaymentInfoDTO() {
	}

	public int getidClientPaymentInfo() {
		return this.idClientPaymentInfo;
	}

	public void setidClientPaymentInfo(int idClientPaymentInfo) {
		this.idClientPaymentInfo = idClientPaymentInfo;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
}