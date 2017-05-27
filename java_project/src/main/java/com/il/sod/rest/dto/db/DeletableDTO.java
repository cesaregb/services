package com.il.sod.rest.dto.db;

public abstract class DeletableDTO {

	private int deleted;

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}