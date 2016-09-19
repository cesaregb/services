package com.il.sod.rest.dto.db;

public class ClientBagDTO {

	private int idClientBags;
	private boolean inOrder;
	private String number;
	private BagSizeDTO bagSize;
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

	public BagSizeDTO getBagSize() {
		return bagSize;
	}

	public void setBagSize(BagSizeDTO bagSize) {
		this.bagSize = bagSize;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
}