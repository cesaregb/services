package com.il.sod.rest.dto.db;

public class ClientPaymentInfoDTO {

	private int idClientDTOPaymentInfo;

	private String token;

	private int type;

	private Integer client;

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

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}
}