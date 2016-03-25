package com.il.sod.db.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


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
	private List<OrderTypeTask> orderTypeTasks;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="orderType", fetch=FetchType.EAGER)
	private List<Order> orders;
	
	//bi-directional many-to-many association to ServiceType
	@ManyToMany(mappedBy="orderTypes", fetch=FetchType.EAGER)
	private List<ServiceType> serviceTypes;

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

	public List<OrderTypeTask> getOrderTypeTasks() {
		return this.orderTypeTasks;
	}

	public void setOrderTypeTasks(List<OrderTypeTask> orderTypeTasks) {
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

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
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
	
	public List<ServiceType> getServiceTypes() {
		return this.serviceTypes;
	}

	public void setServiceTypes(List<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
}
