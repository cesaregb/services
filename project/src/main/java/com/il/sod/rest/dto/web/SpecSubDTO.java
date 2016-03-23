package com.il.sod.rest.dto.web;

import com.il.sod.rest.dto.KeyValue;

public class SpecSubDTO {
	private int idSpec;
    private String comments;
    private KeyValue product;
    
	public int getIdSpec() {
		return idSpec;
	}
	public void setIdSpec(int idSpec) {
		this.idSpec = idSpec;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public KeyValue getProduct() {
		return product;
	}
	public void setProduct(KeyValue product) {
		this.product = product;
	}
}
