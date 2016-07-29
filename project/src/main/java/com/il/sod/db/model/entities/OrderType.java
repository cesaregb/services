package com.il.sod.db.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the OrderType database table.
 *
 */
@Entity
@NamedQuery(name="OrderType.findAll", query="SELECT o FROM OrderType o")
public class OrderType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderType;

	private String description;

	private String name;

	//bi-directional many-to-one association to OrderTypeTask
	@OneToMany(mappedBy="orderType", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<OrderTypeTask> orderTypeTasks;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="orderType", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Order> orders;
	
	private int transportInfo;

	public OrderType() {
	}

	public int getIdOrderType() {
		return this.idOrderType;
	}

	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<OrderTypeTask> getOrderTypeTasks() {
		return this.orderTypeTasks;
	}

	public void setOrderTypeTasks(Set<OrderTypeTask> orderTypeTasks) {
		this.orderTypeTasks = orderTypeTasks;
	}

	public OrderTypeTask addOrderTypeTask(OrderTypeTask orderTypeTask) {
		getOrderTypeTasks().add(orderTypeTask);
		orderTypeTask.setOrderType(this);

		return orderTypeTask;
	}

	public OrderTypeTask removeOrderTypeTask(OrderTypeTask orderTypeTask) {
		getOrderTypeTasks().remove(orderTypeTask);
		orderTypeTask.setOrderType(null);

		return orderTypeTask;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderType(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderType(null);

		return order;
	}
	@Override
	public Integer getId() {
		return this.idOrderType;
	}

	@Override
	public OrderType setId(Integer id) {
		this.idOrderType = id;
		return this;
	}
	
	public int getTransportInfo() {
		return transportInfo;
	}

	public void setTransportInfo(int transportInfo) {
		this.transportInfo = transportInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + idOrderType;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderTypeTasks == null) ? 0 : orderTypeTasks.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + transportInfo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderType other = (OrderType) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idOrderType != other.idOrderType)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderTypeTasks == null) {
			if (other.orderTypeTasks != null)
				return false;
		} else if (!orderTypeTasks.equals(other.orderTypeTasks))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (transportInfo != other.transportInfo)
			return false;
		return true;
	}
}
