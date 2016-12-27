package com.il.sod.rest.dto.db;

import com.google.common.base.Objects;

import java.util.List;
import java.util.Set;

public class OrderTypeDTO extends DeletableDTO{
	private int idOrderType;
	private String description;
	private String name;
	private Set<Integer> orders;
	private List<OrderTypeTaskDTO> OrderTypeTask;
	private Integer transportInfo;
	public int getIdOrderType() {
		return idOrderType;
	}
	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Integer> getOrders() {
		return orders;
	}
	public void setOrders(Set<Integer> orders) {
		this.orders = orders;
	}

	public List<OrderTypeTaskDTO> getOrderTypeTask() {
		return OrderTypeTask;
	}

	public void setOrderTypeTask(List<OrderTypeTaskDTO> OrderTypeTask) {
		this.OrderTypeTask = OrderTypeTask;
	}

	public Integer getTransportInfo() {
		return transportInfo;
	}

	public void setTransportInfo(Integer transportInfo) {
		this.transportInfo = transportInfo;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("idOrderType", idOrderType)
				.add("description", description)
				.add("name", name)
				.add("transportInfo", transportInfo)
				.add("deleted", getDeleted())
				.toString();
	}

}

