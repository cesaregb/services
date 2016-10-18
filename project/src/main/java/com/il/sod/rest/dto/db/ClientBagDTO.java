package com.il.sod.rest.dto.db;

public class ClientBagDTO {

	private int idClientBags;
	private boolean inOrder;
	private String number;
	private BagTypeDTO bagType;
	private int idClient;

	public int getIdClientBags() {
		return idClientBags;
	}

	public void setIdClientBags(int idClientBags) {
		this.idClientBags = idClientBags;
	}

	public boolean isInOrder() {
		return inOrder;
	}

	public void setInOrder(boolean inOrder) {
		this.inOrder = inOrder;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BagTypeDTO getBagType() {
		return bagType;
	}

	public void setBagType(BagTypeDTO bagType) {
		this.bagType = bagType;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
}