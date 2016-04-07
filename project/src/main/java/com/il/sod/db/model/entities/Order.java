package com.il.sod.db.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Orders database table.
 *
 */
@Entity
@Table(name="Orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrder;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	private int time;
	
	private int idAddressDeliver;

	private int idAddressPickup;

	private double price;

	private int status;

	//bi-directional many-to-one association to OrderTask
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private Set<OrderTask> orderTasks;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;

	//bi-directional many-to-one association to OrderType
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idOrderType")
	private OrderType orderType;
	
	//bi-directional many-to-one association to OrderPickNDeliver
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<OrderPickNDeliver> orderPickNdelivers;
	
	//bi-directional many-to-one association to PaymentInfo
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<PaymentInfo> paymentInfos;
	
	private Date pickUpDate;
	private Date deliverDate;
	
	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Service> services;

	public Order() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public Order setIdOrder(int idOrder) {
		this.idOrder = idOrder;
		return this;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public Set<OrderTask> getOrderTasks() {
		return this.orderTasks;
	}

	public void setOrderTasks(Set<OrderTask> orderTasks) {
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

	public OrderType getOrderType() {
		return this.orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	@Override
	public Integer getId() {
		return this.idOrder;
	}

	@Override
	public Order setId(Integer id) {
		this.idOrder = id;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public Set<OrderPickNDeliver> getOrderPickNdelivers() {
		return this.orderPickNdelivers;
	}

	public void setOrderPickNdelivers(Set<OrderPickNDeliver> orderPickNdelivers) {
		this.orderPickNdelivers = orderPickNdelivers;
	}

	public OrderPickNDeliver addOrderPickNdeliver(OrderPickNDeliver orderPickNdeliver) {
		getOrderPickNdelivers().add(orderPickNdeliver);
		orderPickNdeliver.setOrder(this);

		return orderPickNdeliver;
	}

	public OrderPickNDeliver removeOrderPickNdeliver(OrderPickNDeliver orderPickNdeliver) {
		getOrderPickNdelivers().remove(orderPickNdeliver);
		orderPickNdeliver.setOrder(null);

		return orderPickNdeliver;
	}
	
	public Set<PaymentInfo> getPaymentInfos() {
		return this.paymentInfos;
	}

	public void setPaymentInfos(Set<PaymentInfo> paymentInfos) {
		this.paymentInfos = paymentInfos;
	}

	public PaymentInfo addPaymentInfo(PaymentInfo paymentInfo) {
		getPaymentInfos().add(paymentInfo);
		paymentInfo.setOrder(this);

		return paymentInfo;
	}

	public PaymentInfo removePaymentInfo(PaymentInfo paymentInfo) {
		getPaymentInfos().remove(paymentInfo);
		paymentInfo.setOrder(null);

		return paymentInfo;
	}
	
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	
	public Set<Service> getServices() {
		return this.services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		if (services == null){
			services = new HashSet<>();
		}
		getServices().add(service);
		service.setOrder(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setOrder(null);

		return service;
	}
}
