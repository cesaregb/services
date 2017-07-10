package com.il.sod.db.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.il.sod.exception.SODAPIException;

import javax.persistence.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the Orders database table.
 */
@Entity
@Table(name = "Orders")
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
public class Order extends SoftDeleteEntity implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idOrder;

  private String comments;

  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  private int time;

  private int idAddressDeliver;

  private int idAddressPickup;

  private double totalServices;

  private double total;

  private int status;

  //bi-directional many-to-one association to OrderTask
  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<OrderTask> orderTasks;

  //bi-directional many-to-one association to Client
  @ManyToOne
  @JoinColumn(name = "idClient")
  private Client client;

  //bi-directional many-to-one association to OrderType
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
  @JoinColumn(name = "idOrderType")
  @JsonBackReference
  private OrderType orderType;

  //bi-directional many-to-one association to PaymentInfo
  @OneToOne(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonManagedReference
  private PaymentInfo paymentInfo;

  private Date pickUpDate;
  private Date deliverDate;

  //bi-directional many-to-one association to Service
  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<Service> services;

  private int createdBy;

  private double pickUpPrice;
  private double deliverPrice;
  private double discount;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private Set<OrderPriceAdjustment> orderPriceAdjustments;

  private boolean paymentStatus;

  private int idCashOut;

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

  public double getTotalServices() {
    return totalServices;
  }

  public void setTotalServices(double totalServices) {
    this.totalServices = totalServices;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
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

  @Transient
  public OrderTask getServiceOrderTask() throws SODAPIException {
    return this.orderTasks.stream()
            .filter(o -> o.getTask().getId() == 1)
            .findFirst()
            .orElseThrow(() -> new SODAPIException(Response.Status.INTERNAL_SERVER_ERROR, "Service task not found!"));
  }

  public void setOrderTasks(Set<OrderTask> orderTasks) {
    this.orderTasks = orderTasks;
  }

  public OrderTask addOrderTask(OrderTask orderTask) {
    if (orderTasks == null) {
      orderTasks = new HashSet<>();
    }
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
    if (services == null) {
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

  public int getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }

  public PaymentInfo getPaymentInfo() {
    return paymentInfo;
  }

  public void setPaymentInfo(PaymentInfo paymentInfo) {
    this.paymentInfo = paymentInfo;
  }


  public Set<OrderPriceAdjustment> getOrderPriceAdjustments() {
    return orderPriceAdjustments;
  }

  public void setOrderPriceAdjustments(Set<OrderPriceAdjustment> orderPriceAdjustments) {
    this.orderPriceAdjustments = orderPriceAdjustments;
  }

  public OrderPriceAdjustment addOrderPriceAdjustment(OrderPriceAdjustment orderPriceAdjustment) {
    getOrderPriceAdjustments().add(orderPriceAdjustment);
    orderPriceAdjustment.setOrder(this);
    return orderPriceAdjustment;
  }

  public OrderPriceAdjustment removeOrderPriceAdjustment(OrderPriceAdjustment orderPriceAdjustment) {
    getOrderPriceAdjustments().remove(orderPriceAdjustment);
    orderPriceAdjustment.setOrder(null);
    return orderPriceAdjustment;
  }

  public double getPickUpPrice() {
    return pickUpPrice;
  }

  public void setPickUpPrice(double pickUpPrice) {
    this.pickUpPrice = pickUpPrice;
  }

  public double getDeliverPrice() {
    return deliverPrice;
  }

  public void setDeliverPrice(double deliverPrice) {
    this.deliverPrice = deliverPrice;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public boolean isPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(boolean paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public int getIdCashOut() {
    return idCashOut;
  }

  public void setIdCashOut(int idCashOut) {
    this.idCashOut = idCashOut;
  }

  @Transient
  public Double getSubtotal(){
    return this.getTotalServices() + this.getPickUpPrice() + this.getDeliverPrice();
  }
}
