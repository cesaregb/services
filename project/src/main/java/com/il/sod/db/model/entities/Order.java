package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Orders database table.
 * 
 */
@Entity
@Table(name="Orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrder;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int idAddressDeliver;

	private int idAddressPickup;

	private double price;

	private int status;

	//bi-directional many-to-one association to OrderTask
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private List<OrderTask> orderTasks;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;

	//bi-directional many-to-one association to OrderTemplate
	@ManyToOne
	@JoinColumn(name="idOrderTemplate")
	private OrderTemplate orderTemplate;

	public Order() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdAddressDeliver() {
		return this.idAddressDeliver;
	}

	public void setIdAddressDeliver(int idAddressDeliver) {
		this.idAddressDeliver = idAddressDeliver;
	}

	public int getIdAddressPickup() {
		return this.idAddressPickup;
	}

	public void setIdAddressPickup(int idAddressPickup) {
		this.idAddressPickup = idAddressPickup;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<OrderTask> getOrderTasks() {
		return this.orderTasks;
	}

	public void setOrderTasks(List<OrderTask> orderTasks) {
		this.orderTasks = orderTasks;
	}

	public OrderTask addOrderTask(OrderTask orderTask) {
		getOrderTasks().add(orderTask);
		orderTask.setOrder(this);

		return orderTask;
	}

	public OrderTask removeOrderTask(OrderTask orderTask) {
		getOrderTasks().remove(orderTask);
		orderTask.setOrder(null);

		return orderTask;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public OrderTemplate getOrderTemplate() {
		return this.orderTemplate;
	}

	public void setOrderTemplate(OrderTemplate orderTemplate) {
		this.orderTemplate = orderTemplate;
	}

}