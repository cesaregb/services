package com.il.sod.rest.dto.db;

public class ClientPaymentInfoDTO {

	private int idClientDTOPaymentInfo;
	private String token;
	private int type;
	private int idClient;

	public ClientPaymentInfoDTO() {
	}

	public int getIdClientDTOPaymentInfo() {
		return this.idClientDTOPaymentInfo;
	}

	public void setIdClientDTOPaymentInfo(int idClientDTOPaymentInfo) {
		this.idClientDTOPaymentInfo = idClientDTOPaymentInfo;
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