package com.il.sod.rest.dto.db;

public class BagSizeDTO{

	private int idBagSize;
	private String name;
	private int size;

	public int getIdBagSize() {
		return idBagSize;
	}

	public void setIdBagSize(int idBagSize) {
		this.idBagSize = idBagSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}