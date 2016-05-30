package com.il.sod.rest.dto;

public class KeyValue<K, V> {
	private K key;
	private V value;
	private Double serviceIncrement;
	
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
}
