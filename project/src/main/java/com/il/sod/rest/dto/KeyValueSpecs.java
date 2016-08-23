package com.il.sod.rest.dto;

public class KeyValueSpecs<K, V> {
	private K key;
	private V value;
	private Double serviceIncrement;
	private Double specPrice;
	private int costType;
	
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public Double getServiceIncrement() {
		return serviceIncrement;
	}
	public void setServiceIncrement(Double serviceIncrement) {
		this.serviceIncrement = serviceIncrement;
	}
	public Double getSpecPrice() {
		return specPrice;
	}
	public void setSpecPrice(Double specPrice) {
		this.specPrice = specPrice;
	}
	public int getCostType() {
		return costType;
	}
	public void setCostType(int costType) {
		this.costType = costType;
	}
}
